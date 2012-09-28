package eoss.validator;

/**
 * Interface for Visitor pattern. Target of visitor objects are members of the
 * HTML composite tree.
 * 
 * @author Thomas Kohlman
 * 
 */
public interface ITagVisitor {
	void Visit(RootTag tag);

	void Visit(HtmlTag tag);

	void Visit(HtmlString tag);

	void Visit(HtmlWhitespace tag);

	void Visit(HeadTag tag);

	void Visit(BodyTag tag);

	void Visit(TitleTag tag);

	void Visit(BoldTag tag);

	void Visit(EmphasisTag tag);

	void Visit(TableTag tag);

	void Visit(TrTag tag);

	void Visit(TdTag tag);

	void Visit(H1Tag tag);

	void Visit(H2Tag tag);

	void Visit(H3Tag tag);

	void Visit(H4Tag tag);

	void Visit(H5Tag tag);

	void Visit(H6Tag tag);

	void Visit(OlTag tag);

	void Visit(UlTag tag);

	void Visit(LiTag tag);

	void Visit(DlTag tag);

	void Visit(DtTag tag);

	void Visit(DdTag tag);
}
