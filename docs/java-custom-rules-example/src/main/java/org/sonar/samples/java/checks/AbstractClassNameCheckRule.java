package org.sonar.samples.java.checks;


import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;

/**
 * 类<code>Doc</code>用于：抽象类命名使用 Abstract 或 Base 开头
 *
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2023-04-23
 */
@Rule(key = "AbstractClassNameCheckRule")
public class AbstractClassNameCheckRule extends IssuableSubscriptionVisitor {
  //  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractClassNameCheckRule.class);
  @Override
  public List<Tree.Kind> nodesToVisit() {
    return Collections.singletonList(Tree.Kind.CLASS);
  }

  @Override
  public void visitNode(Tree tree) {
    ClassTree abstractClass = (ClassTree) tree;
    String className = abstractClass.simpleName().name();
    Symbol.TypeSymbol symbol = classTree.symbol();
    
    if (symbol.isAbstract() && !className.startsWith(ABSTRACT_PREFIX) && !className.startsWith(BASE_PREFIX)) {
      reportIssue(classTree.simpleName(), "Abstract class name should start with 'Abstract' or 'Base'");
    }
  }
}
