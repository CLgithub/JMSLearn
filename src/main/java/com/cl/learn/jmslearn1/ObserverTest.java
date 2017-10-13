package com.cl.learn.jmslearn1;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by L on 17/10/12.
 */
public class ObserverTest {
    public static void main(String[] args){
        MyObservable myObservable=new MyObservable();
        myObservable.addObserver(new Observer() {
            //注册匿名内部类Observer，当数据改变时将通知该类的update方法
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("有数据变化"+arg);
            }
        });


        String sValue="Hello Msg";
        myObservable.changeValue(sValue);   //当数据发送变化，
        myObservable.notifyObservers(sValue+"!"); //数据的改变由observable主动通知给Observer
    }
}
