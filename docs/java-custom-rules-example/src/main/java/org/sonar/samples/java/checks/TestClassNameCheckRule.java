package org.sonar.samples.java.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;

/**
 * 类<code>Doc</code>用于：测试类命名以它要测试的类的名称开始，以 Test 结尾。
 *
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2023-04-24
 */
@Rule(key = "TestClassNameCheckRule")
public class TestClassNameCheckRule extends IssuableSubscriptionVisitor {

  private static final String TEST_SUFFIX = "Test";

  @Override
  public List<Tree.Kind> nodesToVisit() {
    return Collections.singletonList(Tree.Kind.CLASS);
  }

  @Override
  public void visitNode(Tree tree) {
    ClassTree testClass = (ClassTree) tree;
    String className = testClass.simpleName().name();

    if (!className.endsWith(TEST_SUFFIX)) {
      reportIssue(testClass.simpleName(), "测试类命名以它要测试的类的名称开始，以 Test 结尾。");
    }
  }
}
