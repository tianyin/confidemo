package checks;

import conf.ParamAttr;

public class ClassChecker extends Checker {

  public static boolean check(String rawValue, String param, ParamAttr pattr) {
    try {
      Class.forName(rawValue);
    } catch (Exception e) {
      System.err.println("requested class could not be loaded");
      return false;
    }
    return true;
  }
}
