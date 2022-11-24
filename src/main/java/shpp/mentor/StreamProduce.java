package shpp.mentor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StreamProduce {
    private final Statement instance;

    public StreamProduce(Statement instance) {

        this.instance = instance;
    }

    public AtomicInteger loadStreamLots(Connection connection, int qtyElements, int qtyArg1, int qtyArg2, int qtyArg3) throws SQLException {
        //Use the counter inside of stream to calculate the qty of elements generate
        AtomicInteger counter = new AtomicInteger();//Atomic type because I can get it from streaming process
        connection.setAutoCommit(false);
        PreparedStatement stat = connection.prepareStatement("INSERT INTO store_lot (store_id,goods_id,qty) VALUES(?,?,?)");
        //Math.random function works quicker than Random.nextInt()
        Stream.generate(() -> new DTO().setName(DTOSubFunction.getName())
                        .setArg1(DTOSubFunction.getNumeric(1, qtyArg1))
                        .setArg2(DTOSubFunction.getNumeric(1, qtyArg2))
                        .setArg3(DTOSubFunction.getNumericFloat(10, qtyArg3)))
                .limit(qtyElements)//Qty of elements
                .forEach((p) -> {//This block send every generated element to queue
                            if (DTOvalidation.getValidate(p).isEmpty()) {
                                try {
                                    counter.getAndIncrement();
                                    stat.setInt(1, p.getArg1());
                                    stat.setInt(2, p.getArg2());
                                    stat.setFloat(3, p.getArg3());
                                    stat.addBatch();
                                    if (counter.intValue() % 100 == 0 || counter.intValue() == qtyElements) {
                                        stat.executeBatch();
                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                );
        connection.commit();
        return counter;//Return as result the qty of sent elements to queue
    }

    public AtomicInteger loadStreamGoods(int qtyElements, int qtyArg1) {
        //Use the counter inside of stream to calculate the qty of elements generate
        AtomicInteger counter = new AtomicInteger();//Atomic type because I can get it from streaming process
        //Math.random function works quicker than Random.nextInt()
        Stream.generate(() -> new DTO().setName(DTOSubFunction.getName())
                        .setArg1(DTOSubFunction.getNumeric(1, qtyArg1))
                        .setArg2(1)
                        .setArg3(1))
                .limit(qtyElements)//Qty of elements
                .forEach((p) -> {//This block send every generated element to queue
                            if (DTOvalidation.getValidate(p).isEmpty()) {
                                try {
                                    instance.execute("INSERT INTO goods (goods_name,type_id) VALUES  " +
                                            "('" + p.getName()
                                            + "'," + p.getArg1()
                                            + ")");
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            counter.getAndIncrement();
                        }
                );
        return counter;//Return as result the qty of sent elements to queue
    }

    public AtomicInteger loadStreamTypes(String list) throws SQLException {
        //Use the counter inside of stream to calculate the qty of elements generate
        String[] typesName = list.split(",");
        AtomicInteger counter = new AtomicInteger();//Atomic type because I can get it from streaming process
        //Math.random function works quicker than Random.nextInt()
        Arrays.stream(typesName).forEach((p) -> {//This block send every generated element to queue
                    try {
                        instance.execute("INSERT INTO types (type_name) VALUES ('" + p + "')");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    counter.getAndIncrement();
                }
        );
        return counter;//Return as result the qty of sent elements to queue
    }

    public AtomicInteger loadStreamStores(int qtyElements, String prefix) {
        //Use the counter inside of stream to calculate the qty of elements generate
        AtomicInteger counter = new AtomicInteger();//Atomic type because I can get it from streaming process
        //Math.random function works quicker than Random.nextInt()
        Stream.generate(() -> new DTO().setName(DTOSubFunction.getName())
                        .setArg1(1)
                        .setArg2(1)
                        .setArg3(1))
                .limit(qtyElements)//Qty of elements
                .forEach((p) -> {//This block send every generated element to queue
                            counter.getAndIncrement();
                            if (DTOvalidation.getValidate(p).isEmpty()) {
                                try {
                                    instance.execute("INSERT INTO stores (store_name, store_address) VALUES  " +
                                            "('Супермаркет №" + counter.intValue() + "', '" + prefix +
                                            p.getName() + " ," + counter.intValue() + "')");
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                );
        return counter;//Return as result the qty of sent elements to queue
    }
}
