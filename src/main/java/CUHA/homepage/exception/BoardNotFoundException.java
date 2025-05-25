package CUHA.homepage.exception;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(Long id) {
        super("Board not found. ID: " + id);
    }

}
