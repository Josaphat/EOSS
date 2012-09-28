package eoss.validator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Root of composite tree.
 * 
 * @author Thomas Kohlman
 * 
 */
public class RootTag implements ITag {
	ArrayList<ITag> children;

	/**
	 * Constructor
	 * 
	 * @param tagStart
	 *            - opening tag
	 * @param tagEnd
	 *            - closing tag
	 */
	public RootTag() {
		children = new ArrayList<ITag>();
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
