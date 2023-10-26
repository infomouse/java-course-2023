package edu.hw2.task3;

import java.util.random.RandomGenerator;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

class FaultyConnectionTest {
    private static final String COMMAND = "cmd";

    private final RandomGenerator randomGenerator = mock(RandomGenerator.class);

    private final Connection connection = new FaultyConnection(randomGenerator);

    @Test
    void testExecuteJustReturns() {
        when(randomGenerator.nextBoolean()).thenReturn(false);

        connection.execute(COMMAND);
    }

    @Test
    void testExecuteThrowsConnectionException() {
        when(randomGenerator.nextBoolean()).thenReturn(true);

        assertThrows(ConnectionException.class, () -> connection.execute(COMMAND));
    }
}
