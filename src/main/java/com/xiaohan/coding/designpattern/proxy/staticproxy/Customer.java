package com.xiaohan.coding.designpattern.proxy.staticproxy;

public class Customer implements IBuyCar {
    //购车款
    private int cash;

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public void buyCar() {
    }
}
