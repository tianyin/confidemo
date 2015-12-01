package conf;

import checks.FileChecker;
import checks.TypeChecker;


public class Params {
  
  @ParamAttr(
    desc="Tachyon installation folder.", 
    scope = Scope.COMMON,
    required=true,
    checks={TypeChecker.class}
  )
  public static final String TACHYON_HOME = "tachyon.home";

  @ParamAttr(
    desc="Set to true to enable debug mode which has additional logging and info in the Web UI.",
    scope = Scope.COMMON,
    checks={TypeChecker.class}
  )
  public static final String TACHYON_DEBUG = "tachyon.debug";
  
  @ParamAttr(
    desc="The path to store log files.",
    scope = Scope.COMMON,
    checks={TypeChecker.class, FileChecker.class}
  )
  public static final String TACHYON_LOGGER_TYPE = "tachyon.logs.dir";
  
}
