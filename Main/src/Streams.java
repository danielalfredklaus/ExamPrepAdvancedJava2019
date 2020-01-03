import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) throws Exception {

        //Kitlei Examples of simple foreach Streams
        Stream.of(1,2,3).forEach(num -> System.out.println(num));
        System.out.println("----");
        Stream.of(1,2,3).forEach(System.out::println);
        System.out.println("----");
        Stream.of(1,2,3,4,5,6).skip(2).forEach(System.out::println);
        System.out.println("----");
        Stream.of(1,2,3,4,5,6).limit(2).forEach(System.out::println);
        System.out.println("----");
        Stream.of(1,2,3,4,5,6).limit(5).skip(2).forEach(System.out::println);
        System.out.println("----");
        Stream.of(1,2,3,4,5,6).takeWhile(n->n<=3).forEach(System.out::println);
        System.out.println("----end of first example series");
        //Stream has 3 parts, Head, Middle, End

        Stream<String > strings = Stream.iterate("a", s->s+s).limit(7);
        Stream.iterate(1, n-> 2 * n).limit(20).filter(n-> n >= 0).forEach(System.out::println);
        System.out.println("----");
        IntStream.of(1,2,3,4,5,6).mapToObj(n->n);
        IntStream.of(1,2,3,4,5,6).boxed();
        //System.out.println("----");
        Stream.of(1,2,3).flatMap(n->IntStream.range(0,n).boxed()).forEach(System.out::println);
        System.out.println("----");

        //Stream.of(1,5,9,159,23,2,3).dropWhile(n->n % 2 == 1).forEach(System.out::println);

        Stream.of(1,5,9,159,23,2,3).sorted(Comparator.comparing(n -> (""+n).length())).forEach(System.out::println);
        System.out.println("----");

        Stream.iterate(1, n->n).parallel().filter(n->n < 10).limit(10).forEach(System.out::println);
        System.out.println("----");

        int[] arrayofInts = IntStream.of(1,2,3).toArray();
        Object[] objectArray = Stream.of(1,5,9,159,23,2,3).toArray();
        Integer[] IntArray = Stream.of(1,5,9,159,23,2,3).toArray(Integer[]::new);
        Integer[] IntArray2 = Stream.of(1,5,9,159,23,2,3).toArray(value -> new Integer[value]);

        String[] StringArray = Stream.of("abc", "cctv").toArray(String[]::new);
        String[] StringArray2 = Stream.of("abc", "cctv").toArray(value -> new String[value]);

        List<String> collect = Stream.of("abc", "cctv").collect(Collectors.toList());
        Set<String> collect2 = Stream.of("abc", "cctv").collect(Collectors.toSet());

        //Print the lengths of the command line arguments in reverse order.
        Stream.of(args).map(arg -> arg.length());
        Stream.of(args).map(String::length).sorted(Comparator.comparing(Function.identity()));
        Stream.of(args).map(String::length).sorted(Comparator.comparing(n->n)); // Alternative to function identity

        String result = Stream.of(args).map(String::length).sorted(Comparator.comparing(Function.<Integer>identity()).reversed()).map(n->n+"").collect(Collectors.joining(","));
        System.out.println(result);

        String result2 = Stream.of(args).sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.joining(", "));
        System.out.println(result2);

        //Print the sum of even numbers that are greater than 8 from the command line arguments.
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Function<Integer, Predicate<Integer>> isGreaterThan = n -> m -> m > n;
        Stream.of(args).map(Integer::parseInt).filter(/* n-> n%2 == 0 */ isEven /*&& n > 8 */).filter(isGreaterThan.apply(8)).forEach(System.out::println);

        //Variant: ignore all texts that are not numbers.
        Predicate<String> isNumber = str -> Pattern.matches("[0-9]+", str); //also gives back long rows of zeroes as 0
        Predicate<String> isNumber2 = str -> {
            try {
                Integer.parseInt(str);
                return true;
            } catch (Exception e) {
                return false;
            }
        };

        //Make a method that generates a stream of the first n prime numbers.
        IntPredicate isPrime =
                n-> IntStream.range(2, n).parallel().allMatch(d-> n % d != 0);

        Function<Integer, String> createPrimes = n-> IntStream.iterate(2, i -> i+1)
                .filter(isPrime)
                .limit(n)
                .mapToObj(num-> num + "")
                .collect(Collectors.joining(", "));


        Integer[] IntArray3 = Stream.of(args).map(Integer::valueOf).sorted().toArray(Integer[]::new);
        //IntStream.of(IntArray3).iterator()

        //Stream.iterate(0, n->n+1).limit(n).filter(n->n % 2 == 1).forEach(System.out::println);
    }

}
