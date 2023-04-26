class LowerCamelCase1 {
  String instanceVariable; // Compliant
  String instancevariable; // Compliant
  String InstanceVariable; // Noncompliant
  String Instancevariable; // Noncompliant
  void methodName(String paramName) {// Compliant
    String localVariable = null;// Compliant
  }

  void MethodName(String ParamName) {// Noncompliant
    String LocalVariable = null;// Noncompliant
  }

  void Methodname(String Paramname) {// Noncompliant
    String Localvariable = null;// Noncompliant
  }

  void methodname(String paramname) {// Compliant
    String localvariable = null;// Compliant
  }
}
