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
  Type type() default Type.GENERAL_STRING;
  Scope scope();
  
  String[] aliases() default {};

  String desc() default "";
  
  String metaVar() default "";

  boolean required() default false;
  
  boolean help() default false;

  boolean hidden() default false;

  String[] depends() default {};
  String[] forbids() default {};
  
  /**
   * Specify the {@link OptionHandler} that processes the command line arguments.
   *
   * <p>
   * The default value {@link OptionHandler} indicates that
   * the {@link OptionHandler} will be infered from
   * the type of the field/method where a {@link Option} annotation
   * is placed.
   *
   * <p>
   * If this annotation element is used, it overrides the inference
   * and determines the handler to be used. This is convenient for
   * defining a non-standard option parsing semantics.
   *
   * <h3>Example</h3>
   *
   * <pre>
   * // this is a normal "-r" option
   * &#64;Option(name="-r")
   * boolean value;
   *
   * // this causes arg4j to use MyHandler, not the default
   * // handler provided for boolean
   * &#64;Option(name="-b",handler=MyHandler.class)
   * boolean value;
   * </pre>
   */
  Class<? extends Checker>[] checks() default Checker.class;
}
