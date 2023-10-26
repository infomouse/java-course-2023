package edu.hw2.task3;

import java.util.random.RandomGenerator;

public class DefaultConnectionManager implements ConnectionManager {
    private final RandomGenerator randomGenerator;

    public DefaultConnectionManager(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public Connection getConnection() {
        if (isFailed()) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }

    private boolean isFailed() {
        return randomGenerator.nextBoolean();
    }
}
