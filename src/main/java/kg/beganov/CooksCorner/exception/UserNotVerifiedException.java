package kg.beganov.CooksCorner.exception;

public class UserNotVerifiedException extends BaseException {
    public UserNotVerifiedException() {
        super("You are not verified! You will get a new verification email");
    }
}
