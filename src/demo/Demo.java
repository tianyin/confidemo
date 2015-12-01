package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;

import conf.ParamAttr;
import conf.Params;
import tachyon.conf.TachyonConf;

/**
 * This is the mock class to demonstrate how the procedure works
 */
public class Demo {

  public static TachyonConf createTachyonConf() throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
    Map<String, ParamAttr> pmap = Params.getParamAttrs();
    
    Properties props = new Properties();
    InputStream siteInputStream = new FileInputStream("./tachyon-default.properties"); 
    props.load(siteInputStream);
    
    /* The checking procedure
     * We may only need to check the configurations set by users from tachyon-site.properties,
     * as the settings in tachyon-default.properties should be tested to be correct.
     */
    for (Map.Entry<Object, Object> entry : props.entrySet()) {
      String key = (String) entry.getKey();
      String val = (String) entry.getValue();
      
      if (pmap.containsKey(key)) {
        System.out.println(key + " " + val);
        
        ParamAttr pattr = pmap.get(key);
        for (int i = 0; i < pattr.checks().length; i++) {
          boolean cres = (Boolean) pattr.checks()[i].getMethod("check", String.class, String.class, ParamAttr.class).invoke(null, val, key, pattr);
          if (!cres) {
            System.err.println("Param: " + key + " is misconfigured.");
            return null;
          }
        }
      } else {
//        System.err.println(key + " is not a valid Tachyon configuration param.");
//        System.err.println(key + " " + val);
      }
    }
    
    return new TachyonConf(props);
  }
  
  public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
    TachyonConf tconf = Demo.createTachyonConf();
    for (Map.Entry<String, String> conf : tconf.toMap().entrySet()) {
      System.out.println(conf.getKey() + " " + conf.getValue());
    }
  }
}
