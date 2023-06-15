package org.example.agent.filter;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoAuthorization {
    String value() default "";
}
