package junit.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * A <code>TestResult</code> collects the results of executing
 * a test case. It is an instance of the Collecting Parameter pattern.
 * The test framework distinguishes between <i>failures</i> and <i>errors</i>.
 * A failure is anticipated and checked for with assertions. Errors are
 * unanticipated problems like an {@link ArrayIndexOutOfBoundsException}.
 *
 * @see Test
 */
public class TestResult {
    protected List<TestFailure> fFailures;
    protected List<TestFailure> fErrors;
    protected List<TestListener> fListeners;
    protected int fRunTests;
    private boolean fStop;

    public TestResult() {
        fFailures = new ArrayList<TestFailure>();
        fErrors = new ArrayList<TestFailure>();
        fListeners = new ArrayList<TestListener>();
        fRunTests = 0;
        fStop = false;
    }

    /**
     * Adds an error to the list of errors. The passed in exception
     * caused the error.
     */
    public synchronized void addError(Test test, Throwable e) {
        /**
         * 添加测试用例错误的相关信息
         */
        fErrors.add(new TestFailure(test, e));
        for (TestListener each : cloneListeners()) {
            /**
             * 执行测试用例的各种监听器的执行错误的方法
             */
            each.addError(test, e);
        }
    }

    /**
     * Adds a failure to the list of failures. The passed in exception
     * caused the failure.
     */
    public synchronized void addFailure(Test test, AssertionFailedError e) {
        /**
         * 添加测试用例失败的相关信息
         */
        fFailures.add(new TestFailure(test, e));
        for (TestListener each : cloneListeners()) {
            /**
             * 执行测试用例的各种监听器的执行失败的方法
             */
            each.addFailure(test, e);
        }
    }

    /**
     * Registers a TestListener
     */
    public synchronized void addListener(TestListener listener) {
        fListeners.add(listener);
    }

    /**
     * Unregisters a TestListener
     */
    public synchronized void removeListener(TestListener listener) {
        fListeners.remove(listener);
    }

    /**
     * Returns a copy of the listeners.
     */
    private synchronized List<TestListener> cloneListeners() {
        List<TestListener> result = new ArrayList<TestListener>();
        result.addAll(fListeners);
        return result;
    }

    /**
     * Informs the result that a test was completed.
     */
    public void endTest(Test test) {
        for (TestListener each : cloneListeners()) {
            each.endTest(test);
        }
    }

    /**
     * Gets the number of detected errors.
     */
    public synchronized int errorCount() {
        return fErrors.size();
    }

    /**
     * Returns an Enumeration for the errors
     */
    public synchronized Enumeration<TestFailure> errors() {
        return Collections.enumeration(fErrors);
    }


    /**
     * Gets the number of detected failures.
     */
    public synchronized int failureCount() {
        return fFailures.size();
    }

    /**
     * Returns an Enumeration for the failures
     */
    public synchronized Enumeration<TestFailure> failures() {
        return Collections.enumeration(fFailures);
    }

    /**
     * Runs a TestCase.
     */
    protected void run(final TestCase test) {
        /**
         * 执行测试用例的各种监听器的开始执行方法
         */
        startTest(test);
        Protectable p = new Protectable() {
            public void protect() throws Throwable {
                /**
                 * 执行测试用例
                 */
                test.runBare();
            }
        };
        /**
         * 执行受保护的测试方法
         */
        runProtected(test, p);
        /**
         * 执行测试用例的各种监听器的结束执行方法
         */
        endTest(test);
    }

    /**
     * Gets the number of run tests.
     */
    public synchronized int runCount() {
        return fRunTests;
    }

    /**
     * Runs a TestCase.
     */
    public void runProtected(final Test test, Protectable p) {
        try {
            /**
             * 执行受保护的测试用例方法
             */
            p.protect();
        } catch (AssertionFailedError e) {
            /**
             * 添加测试失败的相关信息
             */
            addFailure(test, e);
        } catch (ThreadDeath e) { // don't catch ThreadDeath by accident
            throw e;
        } catch (Throwable e) {
            /**
             * 添加测试错误的相关信息
             */
            addError(test, e);
        }
    }

    /**
     * Checks whether the test run should stop
     */
    public synchronized boolean shouldStop() {
        return fStop;
    }

    /**
     * Informs the result that a test will be started.
     */
    public void startTest(Test test) {
        /**
         * 计算测试用例的个数
         */
        final int count = test.countTestCases();
        /**
         * 记录运行的测试用例个数
         */
        synchronized (this) {
            fRunTests += count;
        }
        /**
         * 执行测试用例的各种监听器的开始方法
         */
        for (TestListener each : cloneListeners()) {
            /**
             * 执行测试用例的监听器的开始方法
             */
            each.startTest(test);
        }
    }

    /**
     * Marks that the test run should stop.
     */
    public synchronized void stop() {
        fStop = true;
    }

    /**
     * Returns whether the entire test was successful or not.
     */
    public synchronized boolean wasSuccessful() {
        return failureCount() == 0 && errorCount() == 0;
    }
}
