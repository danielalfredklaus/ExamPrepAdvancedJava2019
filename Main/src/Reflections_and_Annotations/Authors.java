package Reflections_and_Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Make sure the repeatable container has the same @Target and @Retention and other annotations
//like the target annotation!
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authors {
    Author[] value();
}
