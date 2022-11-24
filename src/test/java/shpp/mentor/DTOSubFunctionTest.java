package shpp.mentor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DTOSubFunctionTest {

    @Test
    void getName() {
        boolean actual = (DTOSubFunction.getName()).length()>14;
        boolean expected = true;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void getNumeric() {
        boolean actual = (DTOSubFunction.getNumeric(1,2))<3;
        boolean expected = true;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void getNumericFloat() {
        boolean actual = (DTOSubFunction.getNumericFloat(10,200))<201;
        boolean expected = true;
        Assertions.assertEquals(actual, expected);
    }
}