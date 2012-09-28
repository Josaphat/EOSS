package eoss.validator;

/**
 * Leaf containing text and whitespace.
 * 
 * @author Thomas Kohlman
 * 
 */
public class HtmlString implements ITag {
	protected String string;

	/**
	 * Constructor
	 * 
	 * @param string
	 *            - text contained in this leaf
	 */
	public HtmlString(String string) {
		this.string = string;
	}

	@Override
	public void Accept(ITagVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public void Add(ITag component) throws NotImplementedException {
		throw new NotImplementedException();
	}
}
