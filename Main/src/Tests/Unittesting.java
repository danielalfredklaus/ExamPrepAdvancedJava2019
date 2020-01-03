
import Testing.TestFunctions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import Threads.Threads;

import static org.junit.jupiter.api.Assertions.*;


public class Unittesting {

    // There is BeforeEach, AfterEach, AfterAll, the first is the most important


    @Test
    @Timeout(2000)
    void example(){
        try {
            assertEquals(11, 11);
            assertEquals(13, Threads.fibonacci(7));
        } catch (Exception E){
            E.printStackTrace();
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {1,2,3,11,35})
    void fibs(int n){
        assertTimeout(Duration.ofMillis(100), ()-> {
            try {
                Threads.fibonacci(n);
            } catch (Exception e){
                //
            }
        });

    }

    @ParameterizedTest
    @CsvSource({"1,2","2,3","13,7"})
    void fibs2(int expected, int n) throws Exception {
        assertEquals(expected, Threads.fibonacci(n));
    }

    @ParameterizedTest
    @CsvSource({
            "6,a,false,false",
            "6,'ab',false,true",
            "11,'abc efg',true,true "
    })
    void texts(int expected, String text, boolean b1, boolean b2) {
        assertEquals(expected, (text + (b1 || b2)).length());
    }

    @ParameterizedTest
    @CsvSource({
            "6,a,false",
            "6,'ab',true",
            "11,'abc efg',true "
    })
    void texts2(int expected, String text, boolean aBool) {
        assertEquals(expected, (text + aBool).length());
    }
//    @Test
//    void testPrime(){
//        List.of(2,3,5,7), Testing.TestFunctions.primeStreamer.apply(4).boxed().collect(Collectors.toList());
//    }

    //Tests for summer etc
    @Test
    void summer(){
        assertEquals(10, TestFunctions.summer(1,4));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,100,123})
    void summerManyValues(int max){
        assertEquals(max*(max+1)/2, TestFunctions.summer(1, max));
    }

    @ParameterizedTest
    @CsvSource({"1,3", "5,99"})
    void summerManyValues2(int min, int max){
        //assertEquals(10, TestFunctions.summer(min, max)); Obviously fails
    }
    @ParameterizedTest
    @CsvSource({"1,3,6", "5,99,4940"})
    void summerManyValues3(int min, int max, int expected){
        assertEquals(expected, TestFunctions.summer(min, max));
    }

    //Primestream testing
    @Test
    void primeTest(){
        assertEquals(List.of(2,3,5,7), TestFunctions.primeStreamer.apply(4).boxed().collect(Collectors.toList()));
    }

    @Test
    void testException(){
        assertThrows(IllegalArgumentException.class, ()-> TestFunctions.primeStreamer.apply(-5));
    }
}