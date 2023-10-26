package edu.hw2.task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) {
        int lefAttempts = maxAttempts;
        while (lefAttempts > 0) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                if (--lefAttempts == 0) {
                    throw new ConnectionException("Exceed attempts limit", e);
                }
            } catch (Exception e) {
                throw new ConnectionException("Unexpected exception", e);
            }
        }
    }
}
