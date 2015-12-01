package conf;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import checks.FileChecker;
import checks.TypeChecker;


public class Params {
  
  @ParamAttr(
    desc="Tachyon installation folder.", 
    scope = Scope.COMMON,
    mandatory=true,
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
  
  
  
  public static Map<String, ParamAttr> getParamAttrs() throws IllegalArgumentException, IllegalAccessException {
    Map<String, ParamAttr> pmap = new HashMap<String, ParamAttr>();
    Field[] fields = Params.class.getFields();
    for(Field f : fields){
      Object v = f.get(f.getType());
      if (v instanceof String) {
        ParamAttr p = f.getAnnotation(ParamAttr.class);
        pmap.put((String) v, p);
      }
    }
    return pmap;
  }
}
