package conf;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import checks.Checker;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

@Retention(RUNTIME)
@Target({FIELD,METHOD,PARAMETER})
public @interface ParamAttr {
  String[] aliases() default {};
  
  Type type() default Type.STRING;
  
  // default value
  String defVal();
  
  boolean mandatory() default false;
  boolean hidden() default false;
  
  // Checkers to validate the value
  Class<? extends Checker>[] checks() default Checker.class;
  
  // Used for auto-generate the docs
  Scope scope();
  String desc() default "";

  String[] depends() default {};
  String[] forbids() default {};
}
