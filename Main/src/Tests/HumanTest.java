import Testing.Human;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanTest {

    Human human;

    @BeforeEach
    void beforeEach() {
        human = new Human("John", 20);
    }

    @Test
    void test1(){
        assertEquals("John", human.getName(), "John is called John");
    }

    @Test
    void test2(){
        assertEquals("John", human.getName(), ()-> "John is named John");
    }

    @Test
    void test3(){
        assertEquals(20, human.getAge());
    }

    @Test
    void test4(){
        human.setAge(50);
        assertEquals(50, human.getAge());
    }

    //nested Test with nested Classes
    class HumanWithAge{
        @BeforeEach
        void beforeEach(){
            human.setSalary(100);
        }

        @Test
        void salary(){
            assertEquals(100, human.getSalary());
        }
    }

}
