package com.godtrue.junit.example;

/**
 * @description： 针对加法表达式进行求和计算
 * @author：qianyingjie1
 * @create：2019-11-11
 */
public class Calculator {
    public int eveluate(String expression){
        int sum = 0;
        for(String summand : expression.split("\\+")){
            sum += Integer.valueOf(summand);
        }
        return sum;
    }
}
