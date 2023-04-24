package org.sonar.samples.java.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TypeTree;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * 类<code>Doc</code>用于：检测异常类命名使用 Exception 结尾
 *
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2023-04-24
 */
@Rule(key = "ExceptionClassNameCheckRule")
public class ExceptionClassNameCheckRule extends IssuableSubscriptionVisitor {

  private static final String DEFAULT_EXCEPTION_SUFFIX = "Exception";
  @Override
  public List<Tree.Kind> nodesToVisit() {
    return Collections.singletonList(Tree.Kind.CLASS);
  }

  @Override
  public void visitNode(Tree tree) {
    ClassTree exceptionClass = (ClassTree) tree;
    String className = exceptionClass.simpleName().name();
    Symbol symbol = exceptionClass.symbol();

    if (symbol.type().isSubtypeOf("Exception") && !className.endsWith(DEFAULT_EXCEPTION_SUFFIX)) {
      reportIssue(exceptionClass.simpleName(), "异常类命名使用 Exception 结尾");
    }
  }
}

