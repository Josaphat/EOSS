package eoss.validator;

/**
 * Exception thrown if HTML parsing fails.
 * 
 * @author Thomas Kohlman
 * 
 */
public class HtmlParseException extends Exception {
	private static final long serialVersionUID = -3646213588164756494L;

	public HtmlParseException() {
		super("Error while parsing HTML");
	}

	public HtmlParseException(String message) {
		super(message);
	}

	public HtmlParseException(Throwable cause) {
		super(cause);
	}

	public HtmlParseException(String message, Throwable cause) {
		super(message, cause);
	}
}
