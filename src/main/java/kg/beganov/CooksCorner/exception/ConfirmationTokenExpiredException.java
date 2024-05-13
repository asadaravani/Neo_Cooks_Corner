package kg.beganov.CooksCorner.exception;

public class ConfirmationTokenExpiredException extends BaseException {
    public ConfirmationTokenExpiredException() {
        super("Token expired or already used");
    }
}
