/**
 * The FileBuffer is a collection of Lines. The lines know whether
 * or not they're line-wrapped
 * @author jxv1308
 *
 */
public class FileBuffer {
	private java.util.ArrayList<String> lines;
	private Validator validator;
	
	public boolean validate(){
		return validator.validate();
	}
}