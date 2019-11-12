package junit.samples;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Some simple tests.
 */
public class SimpleTest extends TestCase {
    protected int fValue1;
    protected int fValue2;

    @Override
    protected void setUp() {
        fValue1 = 2;
        fValue2 = 3;
    }

    public static Test suite() {

        /*
           * the type safe way
           *
          TestSuite suite= new TestSuite();
          suite.addTest(
              new SimpleTest("add") {
                   protected void runTest() { testAdd(); }
              }
          );

          suite.addTest(
              new SimpleTest("testDivideByZero") {
                   protected void runTest() { testDivideByZero(); }
              }
          );
          return suite;
          */

        /*
           * the dynamic way
           */
        return new TestSuite(SimpleTest.class);
    }

    public void testAdd() {
        double result = fValue1 + fValue2;
        // forced failure result == 5
        assertTrue(result == 6);
    }

    public int unused;

    public void testDivideByZero() {
        int zero = 0;
        int result = 8 / zero;
        unused = result; // avoid warning for not using result
    }

    public void testEquals() {
        assertEquals(12, 12);
        assertEquals(12L, 12L);
        assertEquals(new Long(12), new Long(12));

        assertEquals("Size", 12, 13);
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

/*

打印信息如下：
1：运行时间
2：方法执行错误的信息
3：方法执行失败的信息
4：方法执行的汇总信息


一个测试用例套件，包含多个测试用例方法，通过测试运行器可以一次性的执行多个测试用例方法


.E.F.F
Time: 0.003
There was 1 error:
1) testDivideByZero(junit.samples.SimpleTest)java.lang.ArithmeticException: / by zero
	at junit.samples.SimpleTest.testDivideByZero(SimpleTest.java:56)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at junit.samples.SimpleTest.main(SimpleTest.java:77)

There were 2 failures:
1) testAdd(junit.samples.SimpleTest)junit.framework.AssertionFailedError
	at junit.samples.SimpleTest.testAdd(SimpleTest.java:49)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at junit.samples.SimpleTest.main(SimpleTest.java:77)
2) testEquals(junit.samples.SimpleTest)junit.framework.AssertionFailedError: Size expected:<12> but was:<13>
	at junit.samples.SimpleTest.testEquals(SimpleTest.java:65)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at junit.samples.SimpleTest.main(SimpleTest.java:77)

FAILURES!!!
Tests run: 3,  Failures: 2,  Errors: 1

 */