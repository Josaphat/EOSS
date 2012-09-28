package eoss.validator;

/**
 * 
 * @author Thomas Kohlman
 * 
 */
public interface ITag {
	/**
	 * Accept method to vacilitate visitors.
	 * 
	 * @param visitor
	 */
	public void Accept(ITagVisitor visitor);

	/**
	 * Add a composite object to this composite.
	 * 
	 * @param component
	 *            - Composite component to add.
	 * @throws NotImplementedException
	 */
	public void Add(ITag component) throws NotImplementedException;
}
