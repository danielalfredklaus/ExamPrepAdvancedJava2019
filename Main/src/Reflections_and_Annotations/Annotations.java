package Reflections_and_Annotations;

import Serialization_Sockets.Book;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@AnAnnotation(value = "notDefault",name = "Dan", age = 24, newNames = {"Mark","Sam"})
public class Annotations {

    //Class Annotation with default values left out
    @AnAnnotation(name = "Mike", age = 24)
    class Anotherclass{
    }

    //Method annotation
    @AnAnnotation(value = "Hello World", name="Gin", age = 24)
    public void doSomething(){}

    //Annotation for method parameter
    public static void doSomethingElse(
            @AnAnnotation(name="aName", value="aValue", age = 24) String parameter){
    }

    //Field annotation
    @AnAnnotation(name="someName",  value = "Hello World")
    public String myField = null;

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws Exception{

        //Reflectively get the annotations in a class
        Class annotationsClass = Annotations.class;
        Annotation[] annotations = annotationsClass.getAnnotations();

        //Check for specific type of Annotations
        for(Annotation annotation : annotations){
            if(annotation instanceof AnAnnotation){
                AnAnnotation myAnnotation = (AnAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }

        //Access method annotations reflectively
        Method method =
                annotationsClass.getMethod("doSomething");
        Annotation[] methodAnnotations = method.getDeclaredAnnotations();

        for(Annotation annotation : methodAnnotations){
            if(annotation instanceof AnAnnotation){
                AnAnnotation myAnnotation = (AnAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }

        //Getting parameter Annotations from methods reflectively
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class[] parameterTypes = method.getParameterTypes();

        int i=0;
        for(Annotation[] paramAnnotations : parameterAnnotations){
            Class parameterType = parameterTypes[i++];

            for(Annotation annotation : paramAnnotations){
                if(annotation instanceof AnAnnotation){
                    AnAnnotation myAnnotation = (AnAnnotation) annotation;
                    System.out.println("param: " + parameterType.getName());
                    System.out.println("name : " + myAnnotation.name());
                    System.out.println("value: " + myAnnotation.value());
                }
            }
        }

        //Get field annotations
        Field field = annotationsClass.getField("myField");
        Annotation[] fieldAnnots = field.getDeclaredAnnotations();

        for(Annotation annotation : fieldAnnots){
            if(annotation instanceof AnAnnotation){
                AnAnnotation myAnnotation = (AnAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }




    }

}
