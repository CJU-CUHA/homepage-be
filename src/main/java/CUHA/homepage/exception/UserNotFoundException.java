package CUHA.homepage.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userName) {
        super("User not found. UserName: " + userName);
    }
}
