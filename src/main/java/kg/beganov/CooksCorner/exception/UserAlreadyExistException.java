package kg.beganov.CooksCorner.exception;

public class UserAlreadyExistException extends BaseException {
    public UserAlreadyExistException() {
        super("User already exists");
    }
}
