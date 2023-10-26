package edu.hw2.task3;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

class FaultyConnectionManagerTest {
    private final ConnectionManager manager = new FaultyConnectionManager();

    @Test
    void testGetConnectionReturnsFaultyConnection() {
        Connection connection = manager.getConnection();

        assertInstanceOf(FaultyConnection.class, connection);
    }
}
