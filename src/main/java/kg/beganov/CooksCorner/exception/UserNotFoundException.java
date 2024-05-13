package kg.beganov.CooksCorner.exception;

public class UserNotFoundException extends BaseException{
    public UserNotFoundException(){
        super("User not found");
    }
}
