package Reflections_and_Annotations;

import Serialization_Sockets.Book;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflections {
    public static void main(String[] args) throws Exception {

        Class bookClass = Book.class;
        //Fields
        Field[] fields   = bookClass.getFields();
        Field field = bookClass.getField("title");
        String fieldName = field.getName();
        Object fieldType = field.getType();
        Book NewBookInstance = new Book("NewTitle",1990);
        Object value = field.get(NewBookInstance);
        field.set(NewBookInstance, value);
        //Methods
        Method[] methods = bookClass.getMethods();
        Method method =
                bookClass.getMethod("setAuthor", String.class);
        Method method2 =
                bookClass.getMethod("getAuthor", null);
        Class[] parameterTypes2 = method.getParameterTypes();
        Class returnType = method2.getReturnType();
        Object returnValue2 = method.invoke(NewBookInstance, "Fred");
        //Getter and Setter example with methods at the end
        printGettersSetters(bookClass);
        //Getting name of a class
        System.out.println("Name of class is "+bookClass.getName());
        System.out.println("Without package name:  "+bookClass.getSimpleName());
        int modifiers = bookClass.getModifiers();

        /* Functions to check modifiers, int is either set or cleared
        Modifier.isAbstract(int modifiers)
        Modifier.isFinal(int modifiers)
        Modifier.isInterface(int modifiers)
        Modifier.isNative(int modifiers)
        Modifier.isPrivate(int modifiers)
        Modifier.isProtected(int modifiers)
        Modifier.isPublic(int modifiers)
        Modifier.isStatic(int modifiers)
        Modifier.isStrict(int modifiers)
        Modifier.isSynchronized(int modifiers)
        Modifier.isTransient(int modifiers)
        Modifier.isVolatile(int modifiers)
        */

        Package pancake = bookClass.getPackage();
        Class superclass = bookClass.getSuperclass();
        Class[] interfaces = bookClass.getInterfaces();
        //Constructors
        Constructor[] constructors = bookClass.getConstructors();
        Constructor singleConstructor =
                bookClass.getConstructor(new Class[]{String.class});
        Book aBook = (Book)
                singleConstructor.newInstance("BookTitle");
        Class[] parameterTypes = singleConstructor.getParameterTypes();
        Annotation[] annotations = bookClass.getAnnotations();

        //Accessing private fields
        PrivateObject privateObject = new PrivateObject("The Private Value");

        Field privateStringField = PrivateObject.class.
                getDeclaredField("privateString");

        privateStringField.setAccessible(true);

        String fieldValue = (String) privateStringField.get(privateObject);
        System.out.println("fieldValue = " + fieldValue);

        Method privateStringMethod = PrivateObject.class.
                getDeclaredMethod("getPrivateString", null);

        privateStringMethod.setAccessible(true);

        String returnValue = (String)
                privateStringMethod.invoke(privateObject, null);

        System.out.println("returnValue = " + returnValue);
    }

    public static void printGettersSetters(Class aClass){
        Method[] methods = aClass.getMethods();

        for(Method method : methods){
            if(isGetter(method)) System.out.println("getter: " + method);
            if(isSetter(method)) System.out.println("setter: " + method);
        }
    }

    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))      return false;
        if(method.getParameterTypes().length != 0)   return false;
        if(void.class.equals(method.getReturnType())) return false;
        return true;
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }
}
