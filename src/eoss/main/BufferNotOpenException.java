package eoss.main;

/**
 * TODO: Remove this class. This exception is thrown when a client attempts to
 * access a FileBuffer which does not exist.
 */
@SuppressWarnings("serial")
public class BufferNotOpenException extends Exception {
	BufferNotOpenException() {
		super("The specified buffer is not open in the document manager.");
	}
}