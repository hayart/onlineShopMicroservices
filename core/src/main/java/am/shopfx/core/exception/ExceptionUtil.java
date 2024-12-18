package am.shopfx.core.exception;

public class ExceptionUtil {

    public static void warn(String message) {
        throw new WarnException(message);
    }

}
