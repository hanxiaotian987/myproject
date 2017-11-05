package com.xiaohan.coding.thread.study.threadscommunication;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程间通信Demo
 * @ClassName:  ThreadsCommunication   
 * @Description:演示Java如何实现线程间通信
 * 参见{@link http://www.importnew.com/26850.html}
 * @author: hanpanpan
 * @date:   2017年10月14日 下午6:12:08   
 * 
 *
 */
public class ThreadsCommunicationDemo {
	public static void main(String[] args) {
//		demo1();
//		demo2();
//		runDAfterABC();
//		runABCWhenAllReady();
		//doTaskWithResultInWorker();
	}
	
	
	/**
	 * 如何让两个线程依次执行？ 利用 thread.join() 
	 * 场景:假设有两个线程，一个是线程 A，另一个是线程 B，两个线程分别依次打印 1-3 三个数字,
	 * 但我们希望 B在 A全部打印 完后再开始打印
	 * A 1, A 2, A 3, B 1, B 2, B 3
	 */
	private static void demo1() {
		Thread A = new Thread(new Runnable() {
			@Override
			public void run() {
				printNumber("A");				
			}
		});
		Thread B = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("B 等待 A 结束才开始执行");
				try {
					A.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				printNumber("B");
			}
		});
		A.start();
		B.start();
	}
	
	/**
	 * 让 两个线程按照指定方式有序交叉运行 利用 object.wait() 和 object.notify() 两个方法来实现
	 * 场景：现在希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3。
	 * A 1, B 1, B 2, B 3, A 2, A 3
	 */
	private static void demo2() {
		Object lock = new Object();
		Thread A = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(lock) {
					System.out.println("A 1");
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("A 2");
					System.out.println("A 3");
				}
			}
		});
		Thread B = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(lock) {
					System.out.println("B 1");
					System.out.println("B 2");
					System.out.println("B 3");
					lock.notify();
				}
			}
		});
		A.start();
		B.start();
	}
	
	/**
	 * 四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是并发执行的，CountDownLatch 适用于一个线程去等待多个线程的情况。
	 * 场景：A B C 三个线程同时运行，各自独立运行完后通知 D；对 D 而言，只要 A B C 都运行完了，D 再开始运行。
	 */
	private static void runDAfterABC() {
		int count = 3;
		CountDownLatch countDownLatch = new CountDownLatch(count);
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("This is D, D is waitting for A,B,C complete");
				try {
					countDownLatch.await();
					System.out.println("This is D,D start working");
					Thread.sleep(1000);
					System.out.println("D work done");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		for(char threadName='A';threadName <= 'C'; threadName++) {
			final String tN = String.valueOf(threadName);
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("This is " + tN + " start working");
					try {
						Thread.sleep(1000);
						System.out.println(tN + " work done");
						countDownLatch.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	/**
	 * 线程 A B C 各自开始准备，直到三者都准备完毕，然后再同时运行 。也就是要实现一种 线程之间互相等待 的效果,使用CyclicBarrier
	 * 场景：三个运动员各自准备，等到三个人都准备好后，再一起跑
	 */
	private static void runABCWhenAllReady() {
		int runner = 3;
		CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);
		final Random random = new Random();
		for(char runnerName='A';runnerName <= 'C';runnerName++) {
			final String rN = String.valueOf(runnerName);
			new Thread(new Runnable() {
				@Override
				public void run() {
					long prepareTime = random.nextInt(1000) + 100;
					System.out.println(rN + " is preparing for time:" + prepareTime);
					try {
						Thread.sleep(prepareTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(rN + " is prepared,waitting for others");
					try {
						//当前运动员已准备好，等待别人准备好
						cyclicBarrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					//// 所有运动员都准备好了，一起开始跑
					System.out.println(rN + " start running");
				}
			}).start();
		}
		
	}
	
	/**
	 * 子线程完成某件任务后，把得到的结果回传给主线程，使用 Callable和futureTask
	 * 场景：让子线程去计算从 1 加到 100，并把算出的结果返回到主线程。
	 */
	private static void doTaskWithResultInWorker() {
		Callable<Integer> callable = new Callable<Integer>(){
			@Override
			public Integer call() throws Exception {
				System.out.println("Task starts");
				Thread.sleep(5000);
				int sum = 0;
				for(int i=0; i<=100; i++) {
					sum += i;
				}
				System.out.println("Task finished and return result");
				return sum;
			}
		};
		FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
		new Thread(futureTask).start();
		System.out.println("Before futureTask.get()");
		try {
			//主线程调用 futureTask.get() 方法时阻塞主线程；
			//然后 Callable 内部开始执行，并返回运算结果；此时 futureTask.get() 得到结果，主线程恢复运行。
			System.out.println("Result:" + futureTask.get());
			System.out.println("After futureTask.get()");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	private static void printNumber(String threadName) {
		int i=0;
		while(i++ < 3) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadName + " print: " + i);
		}
	}
}
