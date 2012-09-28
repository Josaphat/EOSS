package eoss.validator;

/**
 * Factory method class for creating HTML composite tree leaves.
 * 
 * @author Thomas Kohlman
 * 
 */
public class HtmlTextFactory {
	/**
	 * Create a different HTML leaf, based on the contents of str.
	 * 
	 * @param str
	 * @return
	 */
	public ITag Create(String str) {
		if (str.trim().isEmpty()) {
			return new HtmlWhitespace(str);
		} else {
			return new HtmlString(str);
		}
	}
}
