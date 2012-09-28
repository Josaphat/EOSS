package eoss.validator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Composite h4 tag.
 * 
 * @author Thomas Kohlman
 * 
 */
public class H4Tag implements ITag {
	ArrayList<ITag> children;
	String tagStart;
	String tagEnd;

	/**
	 * Constructor
	 * 
	 * @param tagStart
	 *            - opening tag
	 * @param tagEnd
	 *            - closing tag
	 */
	public H4Tag(String tagStart, String tagEnd) {
		children = new ArrayList<ITag>();
		this.tagStart = tagStart;
		this.tagEnd = tagEnd;
	}

	@Override
	public void Accept(ITagVisitor visitor) {
		visitor.Visit(this);

		Iterator<ITag> iter = children.iterator();

		while (iter.hasNext()) {
			iter.next().Accept(visitor);
		}
	}

	@Override
	public void Add(ITag component) throws NotImplementedException {
		children.add(component);
	}
}
