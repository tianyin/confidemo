package conf;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class Parse {
  
  public static Map<String, ParamAttr> xxx() throws IllegalArgumentException, IllegalAccessException {
    Map<String, ParamAttr> pmap = new HashMap<String, ParamAttr>();
    
    Field[] fields = Params.class.getFields();
    for(Field f : fields){
      Object v = f.get(f.getType());
      if (v instanceof String) {
//        System.out.println(v);
        ParamAttr p = f.getAnnotation(ParamAttr.class);
        pmap.put((String) v, p);
      }
    }
    return pmap;
  }
}
