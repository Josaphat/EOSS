package eoss.validator;

/**
 * Exception class defining exception that is thrown when an item is added to a
 * leaf of the HTML composite tree.
 * 
 * @author Thomas Kohlman
 * 
 */
public class NotImplementedException extends Exception {
	private static final long serialVersionUID = -8757244881648677830L;

	public NotImplementedException() {
		super("Method not implemented.");
	}

	public NotImplementedException(String message) {
		super(message);
	}

	public NotImplementedException(Throwable cause) {
		super(cause);
	}

	public NotImplementedException(String message, Throwable cause) {
		super(message, cause);
	}

}
