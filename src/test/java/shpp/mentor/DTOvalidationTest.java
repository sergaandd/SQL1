package shpp.mentor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DTOvalidationTest {
    DTO test=new DTO().setName("Test")
            .setArg1(1)
            .setArg2(1)
            .setArg3(1);
    @Test
    void getValidate() {
        boolean actual = DTOvalidation.getValidate(test).isEmpty();
        boolean expected = true;
        Assertions.assertEquals(actual, expected);
    }
}