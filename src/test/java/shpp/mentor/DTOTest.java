package shpp.mentor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DTOTest {
DTO test=new DTO().setName("Test")
            .setArg1(1)
            .setArg2(1)
            .setArg3(1);
    @Test
    void getName() {
        String actual=test.getName();
        String exspected = "Test";
        Assertions.assertEquals(actual,exspected);
    }

    @Test
    void getArg1() {
        int actual=test.getArg1();
        int exspected = 1;
        Assertions.assertEquals(actual,exspected);
    }

    @Test
    void getArg2() {
        int actual=test.getArg2();
        int exspected = 1;
        Assertions.assertEquals(actual,exspected);
    }

    @Test
    void getArg3() {
        float actual=test.getArg3();
        float exspected = 1;
        Assertions.assertEquals(actual,exspected);
    }
}