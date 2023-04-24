package org.sonar.samples.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2023-04-24
 */
class TestClassNameCheckRuleTest {
  //org/sonar/samples/java/checks/AbstractClassNameCheckRuleTest.java
  //src/test/files/TestClassNameCheckRule.java
  @Test
  public void test() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/TestClassNameCheckRule.java")
      .withCheck(new TestClassNameCheckRule())
      .verifyIssues();
  }
}
