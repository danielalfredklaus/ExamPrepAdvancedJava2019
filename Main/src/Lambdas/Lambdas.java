package Lambdas;
import static Enums.MyDate.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.*;

import static java.lang.System.out;

public class Lambdas {

    Enum<Month> month = Month.June;
    static int counter = 0;
    public static void main(String[] args){
        //Make a lambda that takes a day of the week and a number n, and returns the weekday that is n days after the other one. Keep in mind that n can be a big number, or a negative one.
        BiFunction<WeekDay, Integer, WeekDay> get = (now, days) -> WeekDay.values()[(now.ordinal() + days) % WeekDay.values().length];
        //int counter = 0;
        Supplier<WeekDay> daySupplier = () -> WeekDay.values()[counter++ % WeekDay.values().length];
        System.out.println(daySupplier.get().toString()+" followed by "+daySupplier.get().toString()+ " followed by "+ daySupplier.get().toString());
        //Make a lambda that returns a lambda like the previous one. The “outer” lambda gets a day of the week as a parameter; the returned lambda starts from that day.
        Function<WeekDay, WeekDay> nestedLambda = (weekDay) -> {
            counter = weekDay.ordinal();
            return daySupplier.get();
        };
        System.out.println(nestedLambda.apply(WeekDay.Saturday).toString());




        //Make a lambda that takes two weekdays, and returns whether the first one is earlier in the week than the second one.
        BiFunction<WeekDay, WeekDay, Boolean> weekDayComparer = (weekDay, weekDay2) -> weekDay.ordinal() < weekDay2.ordinal();
        BiPredicate<WeekDay, WeekDay> weekDayComparer2 = (weekDay, weekDay2) -> weekDay.ordinal() < weekDay2.ordinal();
        out.println(weekDayComparer.apply(WeekDay.Monday, WeekDay.Sunday));
        out.println(weekDayComparer.apply(WeekDay.Thursday, WeekDay.Tuesday));

        // Collections.sort(args);
        //Sort the command line arguments based on the number of 'a' characters in them.
        System.out.println(args[0].chars().filter(num -> num == 'a').count() + " number of a's");



        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(33);
        myList.add(255);
        myList.add(-98);
        myList.add(1111111);
        Collections.sort(myList);
        out.println(myList);

        Collections.sort(myList, (i, i2) -> i2 - i);
        myList.sort((i, i2) -> i2 - i);

        myList.sort(
                (i, i2) -> i2.toString().length() - i.toString().length()
        );
        out.println(myList);


        //Various examples by Kitlei
        Function<Integer, Integer> f = (Integer x) -> 2 * x;
        Function<Integer, Integer> f2 = x -> 2 * x;

        BiFunction<int[], Function<Integer, Integer>, Integer> bf = (arr, fun) -> {
            int sum = 0;
            for (int i : arr) {
                sum += fun.apply(i);
            }
            return sum;
        };

        Runnable hello = () -> System.out.println("Hello World");
        Consumer<String> c = x -> System.out.println(x);
        Consumer<String> c2 = Lambdas::myPrint;
        IntConsumer intCons = val -> System.out.println(val / 2);
        intCons.accept(44);
        Supplier<Integer> zeroSupplier = () -> 0;

        int[] trickyHack = {1};



        // Runnable is called with .run, Consumer is called with .accept, (Bi)Function is .apply, Supplier is .get
        // Predicate is .test
        IntSupplier intSupplier = () -> ++trickyHack[0];
        intSupplier.getAsInt();

        IntUnaryOperator intUnaryOperator = i -> i;
        IntBinaryOperator i2i = (i, j) -> i + j;

        System.out.println(zeroSupplier.get());



    }

    static void myPrint(String s){
        System.out.println("myPrint: " + s);
    }
}
