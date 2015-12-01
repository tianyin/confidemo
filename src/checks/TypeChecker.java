package checks;

import conf.ParamType;
import conf.ParamAttr;

public class TypeChecker extends Checker {
  
  public static boolean check(String rawValue, String param, ParamAttr pattr) {
    try {
      if (pattr.type() == ParamType.BOOLEAN) {
        Boolean.parseBoolean(rawValue); // follow the check in TachyonConf
      } else if (pattr.type() == ParamType.INTEGER) {
        Integer.parseInt(rawValue);
      } else if (pattr.type() == ParamType.LONG) {
        Long.parseLong(rawValue);
      } else if (pattr.type() == ParamType.DOUBLE) {
        Double.parseDouble(rawValue);
      } else if (pattr.type() == ParamType.FLOAT) {
        Float.parseFloat(rawValue);
      }
    } catch (Exception e) {
      System.err.println(param + "has wrong type!");
      return false;
    }
    return true;
  }
}
