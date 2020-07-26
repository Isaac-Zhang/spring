package com.sxzhongf.java.event;

import java.util.Observable;
import java.util.Observer;

/**
 * Java {@link Observable} 观察者模式演示
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see Observable
 * @see Observer
 * @since 2020/7/26
 **/
public class ObservableDemo {

    public static void main(String[] args) {
        MyObservable observable = new MyObservable();
        observable.addObserver(new MyObserver());
        observable.notifyObservers("hello, custom observer");
    }

    static class MyObservable extends Observable {
        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(arg);
            clearChanged();
        }

        /**
         * 因为这里发布通知的时候，需要先设置状态，但是这个方法是 protected
         * 因此我们需要手动继承，放开访问限制
         */
        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }
    }

    static class MyObserver implements Observer {

        /**
         * @param o   表示观察者对象
         * @param arg 表示要 观察的 数据
         */
        @Override
        public void update(Observable o, Object arg) {
            System.out.println("接收到观察请求变动：" + arg);
        }
    }
}
