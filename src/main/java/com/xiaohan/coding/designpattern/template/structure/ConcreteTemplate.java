package com.xiaohan.coding.designpattern.template.structure;

public class ConcreteTemplate extends  AbstractTemplate {
    //基本方法的实现
    @Override
    protected void abstractMethod() {
        //业务相关的代码
    }

    //重写父类的方法
    @Override
    public void hookMethod() {
        //业务相关的代码
    }
}
