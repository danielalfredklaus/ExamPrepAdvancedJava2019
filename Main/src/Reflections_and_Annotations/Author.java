package Reflections_and_Annotations;

import java.lang.annotation.*;


//@Retention(RetentionPolicy.RUNTIME) means that the annotation can be accessed
// via reflection at runtime. If you do not set this directive, the annotation will
// not be preserved at runtime, and thus not available via reflection.
@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE) means that the annotation can only be used ontop of
// types (classes and interfaces typically). You can also specify METHOD or FIELD,
// or you can leave the target out alltogether so the annotation can be used for
// both classes, methods and fields.
@Target(ElementType.TYPE)

//The @ in front of the interface marks it as an annotation.
// Once you have defined the annotation you can use it in your code,
// as shown in the earlier examples.
@Repeatable(Authors.class)
public @interface Author {
     String value(); //If an annotation has a single variable, it is conventionally named value
}
