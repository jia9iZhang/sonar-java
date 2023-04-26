package org.sonar.samples.java.checks;

import org.sonar.check.Rule;
import org.sonar.java.model.declaration.MethodTreeImpl;
import org.sonar.java.model.declaration.VariableTreeImpl;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.tree.*;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类<code>Doc</code>用于：方法名、参数名、成员变量、局部变量都统一使用 lowerCamelCase 风格。
 *
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2023-04-25
 */
@Rule(key = "LowerCamelCaseCheckRule")
public class LowerCamelCaseCheckRule extends IssuableSubscriptionVisitor {

  private static final String LOWER_CAMEL_CASE_REGEX = "^[a-z]+(?:[A-Z][a-z]*)*$";
  private static final String ERROR_MESSAGE = "方法名、参数名、成员变量、局部变量都统一使用 lowerCamelCase 风格。";

  @Override
  public List<Tree.Kind> nodesToVisit() {
    return Collections.singletonList(Tree.Kind.CLASS);
  }

  @Override
  public void visitNode(Tree tree) {
    ClassTree classTree = (ClassTree) tree;
    List<Tree> members = classTree.members();
    if (!members.isEmpty()) {
      for (Tree member : members) {
        //解析成员变量树
        if (member.is(Tree.Kind.VARIABLE)) {
          VariableTreeImpl variableTree = (VariableTreeImpl) member;
          if (!isLowerCamelCase(variableTree.symbol().name())) {
            reportIssue(classTree.simpleName(), ERROR_MESSAGE);
          }
        } else {
          //解析方法树
          MethodTreeImpl methodTree = (MethodTreeImpl) member;
          if (!isLowerCamelCase(methodTree.simpleName().name())) {
            reportIssue(classTree.simpleName(), ERROR_MESSAGE);
          }
          methodTree.parameters().forEach(parameter -> {
            if (!isLowerCamelCase(parameter.simpleName().name())) {
              reportIssue(classTree.simpleName(), ERROR_MESSAGE);
            }
          });
          //解析局部成员变量树
          BlockTree blockTree = methodTree.block();
          for (StatementTree statementTree : blockTree.body()) {
            if (statementTree.is(Tree.Kind.VARIABLE)){
              VariableTreeImpl variableTree = (VariableTreeImpl) statementTree;
              if (isLowerCamelCase(variableTree.simpleName().name())){
                reportIssue(classTree.simpleName(), ERROR_MESSAGE);
              }
            }
          }
        }
      }
    }
  }

  public static boolean isLowerCamelCase(String input) {
    return input.matches(LOWER_CAMEL_CASE_REGEX);
  }
}

