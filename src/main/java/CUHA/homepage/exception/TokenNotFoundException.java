package CUHA.homepage.exception;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException() {
        super("JWT Token not found.");
    }
}
