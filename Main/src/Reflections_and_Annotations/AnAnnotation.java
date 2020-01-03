package Reflections_and_Annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

//The @Inherited annotation signals that a custom Java annotation used
// in a class should be inherited by subclasses inheriting from that class.
@Inherited
//The @Documented annotation is used to signal to the JavaDoc tool that
// your custom annotation should be visible in the JavaDoc for classes
// using your custom annotation.
@Documented
public @interface AnAnnotation {


    String value() default ""; //Can now be left out when using the annotation
    String name();
    int age() default 0;
    String[] newNames() default {"Stan","Tom"};

}
