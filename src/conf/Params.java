package conf;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import checks.ClassChecker;
import checks.FileChecker;
import checks.TypeChecker;

/**
 * Annotated information based on
 * http://tachyon-project.org/documentation/v0.8.2/Configuration-Settings.html
 * tachyon-default.properties
 */
public class Params {  
  @ParamAttr(
    desc="Tachyon installation folder.",
    scope = ParamScope.COMMON,
    mandatory=true,
    defVal = "/mnt/tachyon_default_home",
    checks={TypeChecker.class}
  )
  public static final String TACHYON_HOME = "tachyon.home";

  @ParamAttr(
    desc="Set to true to enable debug mode which has additional logging and info in the Web UI.",
    scope = ParamScope.COMMON,
    defVal = "false",
    checks={TypeChecker.class}
  )
  public static final String TACHYON_DEBUG = "tachyon.debug";
  
  @ParamAttr(
    desc="The path to store log files.",
    scope = ParamScope.COMMON,
    defVal = "${tachyon.home}/logs",
    checks={TypeChecker.class, FileChecker.class}
  )
  public static final String TACHYON_LOGGER_TYPE = "tachyon.logs.dir";
  
  @ParamAttr(
    desc="The interval (in milliseconds) between Tachyon master's heartbeats.",
    scope = ParamScope.MASTER,
    type=ParamType.INTEGER,
    defVal = "1000",
    checks={TypeChecker.class}
  )
  public static final String USER_FILE_BUFFER_BYTES = "tachyon.master.heartbeat.interval.ms";

  @ParamAttr(
    desc="The class name of the checkpoint strategy for lineage output files. "
       + "The default strategy is to checkpoint the latest completed lineage, "
       + "i.e. the lineage whose output files are completed. ",
    scope = ParamScope.MASTER,
    defVal = "tachyon.master.lineage.checkpoint.​CheckpointLatestScheduler",
    checks={TypeChecker.class, ClassChecker.class}
  )
  public static final String MASTER_LINEAGE_CHECKPOINT_CLASS = "tachyon.master.lineage.checkpoint.class";
  
  
  /**
   * Return all the Attrs of the Tachyon parameters
   * @return
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   */
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
