package com.cl.learn.jmslearn1;

import java.util.Observable;

/**
 * Created by L on 17/10/12.
 * Observable是一个封装了基本功能的类，比如注册observer（attach功能），
 * 注销observer（detatch功能）等。我们一般只需从Observalbe派生我们自己的观察者。
 * 应该注意的是，Observable必须是“有变化”才触发通知observer这一任务。
 * 即如果我们不主动设置changed属性为true，将不会有任何变化，
 * 也就是说不会有“通知”。因此，设置changed属性的值是我们应用jdk observer 设计模式的关键所在。
 * Observable提供了setChange()来设置changed属性，
 * 符合了“只有observalbe才能直接或间接通知observer”（observable设计模式的）要求
 */
public class MyObservable extends Observable {
    private String data;

    public void changeValue(String fValues){
        data=fValues;
        setChanged();
    }
}
