package shpp.mentor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

class StreamProduceTest {
    Statement myInstance = Mockito.mock(Statement.class);
    @Test
    void loadStreamLots() throws SQLException {
        Connection connectionToDB=Mockito.mock(Connection.class);
        AtomicInteger actual = new StreamProduce(myInstance).loadStreamLots(connectionToDB,
                0,
                5,
                5,
                30);
        int expected = 0;
        Assertions.assertEquals(actual.intValue(), expected);
    }

    @Test
    void loadStreamGoods() {
        AtomicInteger actual = new StreamProduce(myInstance).loadStreamGoods(
                1,
                1);
        int expected = 1;
        Assertions.assertEquals(actual.intValue(), expected);
    }

    @Test
    void loadStreamTypes() throws SQLException {
        AtomicInteger actual = new StreamProduce(myInstance).loadStreamTypes(
                "Drinks");
        int expected = 1;
        Assertions.assertEquals(actual.intValue(), expected);
    }

    @Test
    void loadStreamStores() {
        AtomicInteger actual = new StreamProduce(myInstance).loadStreamStores(
                1, "st.");
        int expected = 1;
        Assertions.assertEquals(actual.intValue(), expected);
    }
}