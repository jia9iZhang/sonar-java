class LowerCamelCase1 {
  String instanceVariable; // compliant
  String instancevariable; // compliant
  String InstanceVariable; // Noncompliant
  String Instancevariable; // Noncompliant
  void methodName(String paramName) {// compliant
    String localVariable = null;// compliant
  }

  void MethodName(String ParamName) {// Noncompliant
    String LocalVariable = null;// Noncompliant
  }

  void Methodname(String Paramname) {// Noncompliant
    String Localvariable = null;// Noncompliant
  }

  void methodname(String paramname) {// compliant
    String localvariable = null;// compliant
  }
}
