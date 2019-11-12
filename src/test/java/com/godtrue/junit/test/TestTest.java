package com.godtrue.junit.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-11
 */
public class TestTest {

    /**
     * 一个简单的测试用例，如果返回结果为 true，则认为测试结果ok
     */
    @Test
    public void test(){
        Assert.assertTrue(new ArrayList().isEmpty());
    }

    /**
     * 一个简单的测试用例，如果抛出了期待的异常，则认为测试结果是ok的
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void outOfBounds(){
        new ArrayList().get(1);
    }

    /**
     * 一个简单的测试用例，如果方法的运行时间超过期待的超时时间，则认为测试结果异常
     */
    @Test(timeout = 3000)
    public void infinity(){
        while (true);
    }

    /**
     * 一个简单的测试用例，如果方法的运行时间小于等于期待的超时时间，则认为测试结果是ok的
     * @throws InterruptedException
     */
    @Test(timeout = 100)
    public void sleep100() throws InterruptedException{
        Thread.sleep(100);
    }

    /**
     * 一个简单的测试用例，虽然期待的 timeout 默认值为 0 ，不过模拟方法执行时间为 100 毫秒，也不会认为方法异常
     * @throws InterruptedException
     */
    @Test
    public void testTimeoutDefault() throws InterruptedException{
        Thread.sleep(100);
    }
}
