package kg.beganov.CooksCorner.exception;

public class ProductNotFoundException extends BaseException{
    public ProductNotFoundException() {
        super("Product Not Found");
    }
}
