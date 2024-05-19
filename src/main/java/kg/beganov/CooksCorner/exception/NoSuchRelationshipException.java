package kg.beganov.CooksCorner.exception;

public class NoSuchRelationshipException extends BaseException{
    public NoSuchRelationshipException() {
        super("Relationship not found");
    }
}
