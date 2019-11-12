package com.godtrue.junit.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Some simple tests.
 */
public class SimpleTest extends TestCase {
    protected int fValue1;
    protected int fValue2;


    /**
     * Constructs a test case with the given name.
     *
     * @param name
     */
    public SimpleTest(String name) {
        super(name);
    }

    /**
     * 设置执行测试用例的真正方法之前调用的方法，用于设置一些执行测试方法之前的参数之用
     */
    @Override
    protected void setUp() {
        fValue1 = 2;
        fValue2 = 3;
    }

    /**
     * 构建一个测试用例套件
     * @return
     */
    public static Test suite() {
        /**
         * 构建测试用例套件实例
         */
          TestSuite suite= new TestSuite();

        /**
         * 添加测试方法，测试 add 方法
         */
        suite.addTest(
              new SimpleTest("add") {
                   protected void runTest() { testAdd(); }
              }
        );

        /**
         * 添加测试方法，测试 testDivideByZero
         */
        suite.addTest(
              new SimpleTest("testDivideByZero") {
                   protected void runTest() { testDivideByZero(); }
              }
        );
      return suite;
    }

    /**
     * 测试加法操作
     */
    public void testAdd() {
        double result = fValue1 + fValue2;
        assertTrue(result == 5);
    }

    public int unused;

    /**
     * 测试除法操作
     */
    public void testDivideByZero() {
        int zero = 0;
        int result = zero / 8 ;
        unused = result; // avoid warning for not using result
    }

    /**
     * 测试相等的方法
     */
    public void testEquals() {
        assertEquals(12, 12);
        assertEquals(12L, 12L);
        assertEquals(new Long(12), new Long(12));

        assertEquals("Size", 12, 12);
        assertEquals("Capacity", 12.0, 11.99, 0.0);
    }

    /**
     * 主函数，测试方法的入口
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 测试运行器，运行一个测试用例套件
         */
        junit.textui.TestRunner.run(suite());
    }
}

/**
 * A <code>TestSuite</code> is a <code>Composite</code> of Tests.
 * It runs a collection of test cases. Here is an example using
 * the dynamic test definition.
 * <pre>
 * TestSuite suite= new TestSuite();
 * suite.addTest(new MathTest("testAdd"));
 * suite.addTest(new MathTest("testDivideByZero"));
 * </pre>
 * <p>
 * Alternatively, a TestSuite can extract the tests to be run automatically.
 * To do so you pass the class of your TestCase class to the
 * TestSuite constructor.
 * <pre>
 * TestSuite suite= new TestSuite(MathTest.class);
 * </pre>
 * <p>
 * This constructor creates a suite with all the methods
 * starting with "test" that take no arguments.
 * <p>
 * A final option is to do the same for a large array of test classes.
 * <pre>
 * Class[] testClasses = { MathTest.class, AnotherTest.class }
 * TestSuite suite= new TestSuite(testClasses);
 * </pre>
 *
 * @see Test
 */


/*

一个测试用例套件，包含多个测试用例方法，通过测试运行器可以一次性的执行多个测试用例方法

..
Time: 0.043

OK (2 tests)

 */