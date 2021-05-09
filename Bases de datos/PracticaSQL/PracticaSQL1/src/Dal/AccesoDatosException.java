package src.Dal;

public class AccesoDatosException extends RuntimeException{

	public AccesoDatosException(String string, Exception e) {
	}

	public AccesoDatosException() {
	}

	public AccesoDatosException(String message) {
		super(message);
	}

	public AccesoDatosException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccesoDatosException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
