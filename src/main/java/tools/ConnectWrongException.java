package tools;

public class ConnectWrongException extends Exception {
    public ConnectWrongException() {}
    public ConnectWrongException(String msg) {
        super(msg);
    }
}