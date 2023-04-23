package org.sonar.samples.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;


/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2023-04-23
 */
class AbstractClassNameCheckRuleTest {
  @Test
  public void test() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/AbstractClassNameCheckRule.java")
      .withCheck(new AbstractClassNameCheckRule())
      .verifyIssues();
  }
}
