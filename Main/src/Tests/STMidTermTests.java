import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class STMidTermTests {
/*
    @BeforeEach
    void init() {

        Invoice inv = createEmptyInvoice();
        Product prod = createGenericProduct();
        inv.addQuantity(product, QUANTITY);

        List items = inv.getItems();
        Item expItem = new Item(inv, product, QUANTITY);
        Item actual = (Item) items.get(0);

    }*/



    @Test
    void testX() {
        Assertions.assertEquals(true, true);
        Assertions.assertTrue(true);
        Assertions.assertFalse(false);

        Thread thread = new Thread();
        Thread.yield();
    }

    @Test
    void testOne(){
        Assertions.assertTrue(checkAreSmallerThanLastDigit(1,2));
    }
    @Test
    void testTwo(){
        Assertions.assertFalse(checkAreSmallerThanLastDigit(4,5));
    }

    @BeforeEach
    void init(){
        Flight flight = new Flight(flightNum);
    }

    @Test
    void test1(){
        Assertions.assertEquals(flightNum, flight.number);
    }
    @Test
    void test2(){
        Assertions.assertEquals("", flight.airlineCode);
    }
    @Test
    void test3(){
        Assertions.assertNull(flight.airline);
    }
    @Test
    void test4(){
        Exception exception = Assertions.assertThrows(InvalidRequestException.class, () -> {
            flight.getMileageAsKm();
        });

        String expectedMessage = "cannot get mileage";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.toLowerCase().contains(expectedMessage));
    }





}
