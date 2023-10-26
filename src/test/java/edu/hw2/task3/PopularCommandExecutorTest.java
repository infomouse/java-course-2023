package edu.hw2.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.junit.jupiter.api.Test;

class PopularCommandExecutorTest {
    private static final int MAX_ATTEMPTS = 3;
    private static final String COMMAND = "cmd";

    private final ConnectionManager connectionManager = mock(ConnectionManager.class);

    private final PopularCommandExecutor executor = new PopularCommandExecutor(connectionManager, MAX_ATTEMPTS);

    @Test
    void testTryExecuteJustReturns() throws Exception {
        Connection connection = mock(Connection.class);
        when(connectionManager.getConnection()).thenReturn(connection);

        executor.tryExecute(COMMAND);

        verify(connection, times(1)).execute(COMMAND);
        verify(connection, times(1)).close();
    }

    @Test
    void testTryExecuteThrowsConnectionExceptionWithCause() throws Exception {
        Exception exception = new ConnectionException("Cause");
        Connection connection = mock(Connection.class);
        doThrow(exception).when(connection).execute(COMMAND);
        when(connectionManager.getConnection()).thenReturn(connection);

        ConnectionException connectionException = assertThrows(ConnectionException.class, () -> executor.tryExecute(COMMAND));

        assertEquals(exception, connectionException.getCause());
        verify(connection, times(MAX_ATTEMPTS)).close();
    }
}
