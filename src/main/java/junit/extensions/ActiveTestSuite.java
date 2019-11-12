package junit.extensions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

/**
 * A TestSuite for active Tests. It runs each
 * test in a separate thread and waits until all
 * threads have terminated.
 * -- Aarhus Radisson Scandinavian Center 11th floor
 */
public class ActiveTestSuite extends TestSuite {
    private volatile int fActiveTestDeathCount;

    public ActiveTestSuite() {
    }

    public ActiveTestSuite(Class<? extends TestCase> theClass) {
        super(theClass);
    }

    public ActiveTestSuite(String name) {
        super(name);
    }

    public ActiveTestSuite(Class<? extends TestCase> theClass, String name) {
        super(theClass, name);
    }

    /**
     * 运行测试用例，且收集测试用例的执行结果
     * @param result
     */
    @Override
    public void run(TestResult result) {
        fActiveTestDeathCount = 0;
        super.run(result);
        waitUntilFinished();
    }

    /**
     * 运行测试用例，且收集测试用例的执行结果
     * @param test
     * @param result
     */
    @Override
    public void runTest(final Test test, final TestResult result) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    // inlined due to limitation in VA/Java
                    //ActiveTestSuite.super.runTest(test, result);
                    test.run(result);
                } finally {
                    ActiveTestSuite.this.runFinished();
                }
            }
        };
        t.start();
    }

    /**
     * 等待知道所有测试用例都运行结束
     */
    synchronized void waitUntilFinished() {
        while (fActiveTestDeathCount < testCount()) {
            try {
                /**
                 * 阻塞主线程
                 */
                wait();
            } catch (InterruptedException e) {
                return; // ignore
            }
        }
    }

    /**
     * 运行结束，唤醒主线程
     */
    synchronized public void runFinished() {
        fActiveTestDeathCount++;
        notifyAll();
    }
}