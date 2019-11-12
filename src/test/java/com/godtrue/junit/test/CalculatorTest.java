package com.godtrue.junit.test;

import com.godtrue.junit.example.Calculator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @description： 利用 Junit 进行加法表达式的计数器计算
 * @author：qianyingjie1
 * @create：2019-11-11
 */
public class CalculatorTest {

    /**
     * 执行ok的例子
     */
    @Test
    public void evaluatesExpressionRight(){
        Calculator calculator = new Calculator();
        int sum = calculator.eveluate("1+2+3");

        System.out.println("sum is : " + sum);

        Assert.assertEquals("test",6,sum);
    }

    /**
     * 执行错误的例子，一般都不会这么写的，这是就是为了对比演示
     */
    @Test
    public void evaluatesExpressionError(){
        Calculator calculator = new Calculator();
        int sum = calculator.eveluate("1+2+3");

        System.out.println("sum is : " + sum);

        Assert.assertEquals("test",66,sum);
    }
}
