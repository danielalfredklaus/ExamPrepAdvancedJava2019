import Testing.TestFunctions;
import Threads.Threads;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class FibTest {
    @Test
    void OnlyUseASingleTestCaseEachFunction(){
        assertEquals(11, TestFunctions.summer(5,6));
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void alwaysFail(){
        while (true){
            //blank
        }
    }


    @Test
    void timeout(){
        assertTimeout(Duration.ofSeconds(2), ()-> {
            while (true){

            }
        });
    }

    @ParameterizedTest(name = "Test case #{index} is: fib({1}) =?= {0}")
    @CsvSource({"1,2","2,3","13,7"})
    void fib2(int expected, int n)throws Exception {
        assertEquals(expected, Threads.fibonacci(n));
    }
}
