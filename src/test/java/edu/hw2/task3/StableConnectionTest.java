package edu.hw2.task3;

import org.junit.jupiter.api.Test;

class StableConnectionTest {
    private static final String COMMAND = "cmd";

    private final Connection connection = new StableConnection();

    @Test
    void testExecuteJustReturns() {
        connection.execute(COMMAND);
    }
}
