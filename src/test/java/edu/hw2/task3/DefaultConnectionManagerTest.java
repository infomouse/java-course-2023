package edu.hw2.task3;

import java.util.random.RandomGenerator;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

class DefaultConnectionManagerTest {
    private final RandomGenerator randomGenerator = mock(RandomGenerator.class);

    private final ConnectionManager manager = new DefaultConnectionManager(randomGenerator);

    @Test
    void testGetConnectionReturnsStableConnection() {
        when(randomGenerator.nextBoolean()).thenReturn(false);

        Connection connection = manager.getConnection();

        assertInstanceOf(StableConnection.class, connection);
    }

    @Test
    void testGetConnectionReturnsFaultyConnection() {
        when(randomGenerator.nextBoolean()).thenReturn(true);

        Connection connection = manager.getConnection();

        assertInstanceOf(FaultyConnection.class, connection);
    }
}
