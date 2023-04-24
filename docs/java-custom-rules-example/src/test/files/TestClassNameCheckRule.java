package org.sonar.samples.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * 类<code>Doc</code>用于：ExceptionClassNameCheckRule的单元测试
 *
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2023-04-24
 */
class ExceptionClassNameCheckRuleTest {
  @Test
  public void test() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/ExceptionClassNameCheckRule.java")
      .withCheck(new ExceptionClassNameCheckRule())
      .verifyIssues();
  }
}
