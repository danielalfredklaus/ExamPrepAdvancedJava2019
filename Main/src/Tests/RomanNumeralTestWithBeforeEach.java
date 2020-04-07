import SoftwareTesting.RomanNumeral;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;

public class RomanNumeralTestWithBeforeEach {

    public RomanNumeral roman;

    @BeforeEach
    public void initialize() {
        //this.roman = new RomanNumeral();
        this.roman = Mockito.spy(RomanNumeral.class);
        Mockito.when(roman.convert(anyString())).thenReturn(-2);
    }

    @Test
    public void singleNumber() {
        Mockito.when(roman.convert("I")).thenReturn(-10);
        int result = roman.convert("I");

        Assertions.assertEquals(1, result);
        Mockito.verify(roman, Mockito.atLeast(3)).convert("I");
    }
}