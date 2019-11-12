# JUnit 4
JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.

For more information, please visit:
* [Wiki](https://github.com/junit-team/junit4/wiki)
* [Download and Install guide](https://github.com/junit-team/junit4/wiki/Download-and-Install)
* [Getting Started](https://github.com/junit-team/junit4/wiki/Getting-started)

[![Latest Build Status](https://junit.ci.cloudbees.com/job/JUnit/badge/icon)](https://junit.ci.cloudbees.com/)

[![Built on DEV@cloud](http://www.cloudbees.com/sites/default/files/Button-Built-on-CB-1.png)](http://www.cloudbees.com/foss/foss-dev.cb)


注意：一定要好好阅读对应的超文本文件

告诉我们如何使用一个简单的框架，进行可重用测试用例的开发
JUnit Test Infected: Programmers Love Writing Tests
http://localhost:63342/junit4/doc/testinfected/testing.htm

告诉我们如何实现一个简单的测试框架
JUnit A Cook's Tour
http://localhost:63342/junit4/doc/cookstour/cookstour.htm


测试的核心类：

1：Assert 断言判断测试的结果是否ok，可以选择是否打印对应的异常信息，免于打印日志

2：Test 测试的根接口

3：TestCase 测试用例，非常关键，定义了测试方法执行前、执行后、及执行待测方法等的方法

4：TestResult 测试执行的结果信息，包括：运行了多少测试用例、失败多少、错误多少、花费多少时间等

5：TestSuite 测试套件，可以一次性测试多个用例

6：TestRunner 测试执行器，用于执行对应的测试用例





