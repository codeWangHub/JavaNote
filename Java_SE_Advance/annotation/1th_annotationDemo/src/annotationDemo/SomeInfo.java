package annotationDemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author codewang
 *	自定义注解  someInfo
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SomeInfo {
	
	public String name() default "codewang";	
	public int  age() default 20;
	public String info() default "hello world!";
}
