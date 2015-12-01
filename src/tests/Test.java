package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;

import conf.ParamAttr;
import conf.Parse;
import tachyon.conf.TachyonConf;

public class Test {
  
  public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
    Map<String, ParamAttr> pmap = Parse.xxx();
    
    Properties props = new Properties();
    InputStream siteInputStream = new FileInputStream("./tachyon-default.properties"); 
    props.load(siteInputStream);
    
    for (Map.Entry entry : props.entrySet()) {
      String key = (String) entry.getKey();
      String val = (String) entry.getValue();
      
      if (pmap.containsKey(key)) {
        System.out.println(key + " " + val);
        
        ParamAttr pattr = pmap.get(key);
        for (int i = 0; i < pattr.checks().length; i++) {
          System.out.println(pattr.checks()[i].getMethod("check", ParamAttr.class).invoke(null, pattr));
        }
        
      } else {
        // System.err.println(key + " is not a valid Tachyon configuration param.");
      }
    }
    
    TachyonConf tconf = new TachyonConf(props);
    
  }
}
