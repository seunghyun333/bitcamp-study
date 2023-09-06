package bitcamp.personalapp.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
<<<<<<< HEAD
  String value() default "";
=======
	String value() default "";
>>>>>>> af9fe71ea72e61fa4ce6618418f1555e4f4340b0
}
