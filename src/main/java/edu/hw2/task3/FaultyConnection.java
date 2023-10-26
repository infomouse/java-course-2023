package edu.hw2.task3;

import java.util.Random;
import java.util.random.RandomGenerator;

public class FaultyConnection implements Connection {
    private final RandomGenerator randomGenerator;

    public FaultyConnection() {
        this(new Random());
    }

    public FaultyConnection(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public void execute(String command) {
        if (isFailed()) {
            throw new ConnectionException("Fail to execute command");
        }
    }

    @Override
    public void close() {
    }

    private boolean isFailed() {
        return randomGenerator.nextBoolean();
    }
}
