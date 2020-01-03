package Testing;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.rangeClosed;

public class TestFunctions {

    public static int summer(int num1, int num2){
        /*int sum = 0;
        for(int i= num1 + 1;i<num2 ;i++ ){
            sum += i;
        }
        return sum;*/

        //Make a function summer that sums the numbers between its two arguments by adding them together one by one.
        return rangeClosed(num1,num2).sum();
    }

    public int shortSummer(int num1, int num2){
        return IntStream.range(num1,num2).sum();
    }

    public String dictionary(String translatable){
    return "nope";
    }

    static IntPredicate isPrime = p -> IntStream.range(2,p).allMatch(n->p%n != 0);

    public static IntFunction<IntStream> primeStreamer =
            n -> {
                if (n < 0) throw new IllegalArgumentException("N smaller than 0");
                return IntStream.iterate(2, i->i+1).filter(isPrime).limit(n);
            };

}
