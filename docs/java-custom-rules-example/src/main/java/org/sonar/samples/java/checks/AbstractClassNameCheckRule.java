package org.sonar.samples.java.checks;

import org.apache.commons.lang3.StringUtils;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;

/**
 * 类<code>Doc</code>用于：
 * 抽象类命名使用 Abstract 或 Base 开头
 * 抽象类名不能只使用 Abstract 或 Base 开头
 *
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2023-04-23
 */
@Rule(key = "AbstractClassNameCheckRule")
public class AbstractClassNameCheckRule extends IssuableSubscriptionVisitor {

  private static final String ABSTRACT_PREFIX = "Abstract";
  private static final String BASE_PREFIX = "Base";

  @Override
  public List<Tree.Kind> nodesToVisit() {
    return Collections.singletonList(Tree.Kind.CLASS);
  }

  @Override
  public void visitNode(Tree tree) {
    ClassTree abstractClass = (ClassTree) tree;
    String className = abstractClass.simpleName().name();
    Symbol symbol = abstractClass.symbol();

    if (symbol.isAbstract()) {
      if (!StringUtils.startsWith(className, ABSTRACT_PREFIX) && !StringUtils.startsWith(className, BASE_PREFIX)) {
        reportIssue(abstractClass.simpleName(), "抽象类命名使用 Abstract 或 Base 开头");
      }
    } else {
      if (className.length() == ABSTRACT_PREFIX.length() || className.length() == BASE_PREFIX.length()) {
        reportIssue(abstractClass.simpleName(), "抽象类名不能只使用 Abstract 或 Base 开头");
      }
    }
  }
}

