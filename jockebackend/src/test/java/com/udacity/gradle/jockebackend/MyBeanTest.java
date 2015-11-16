package com.udacity.gradle.jockebackend;

import org.junit.Test;

public class MyBeanTest {
    @Test
    public void test() {
        MyBean myBean = new MyBean();
        String dataTest = "a";
        myBean.setData(dataTest);
        assert dataTest.equals(myBean.getData());
    }
}