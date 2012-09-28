package eoss.validator;

import java.util.Iterator;

public class ValidationVisitor implements ITagVisitor {
	private boolean isValid = true;

	public boolean IsValid() {
		return this.isValid;
	}

	public void Reset() {
		this.isValid = true;
	}

	private void ValidateTag(Iterator<ITag> iter,
			Class<? extends ITag>... classes) {
		if (isValid) {
			while ((iter.hasNext()) && this.isValid) {
				boolean pass = false;

				ITag child = iter.next();
				Class<? extends ITag> childClass = child.getClass();

				for (Class<? extends ITag> type : classes) {
					if (type.isAssignableFrom(childClass)) {
						pass = true;
					}
				}

				this.isValid = pass;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(RootTag tag) {
		ValidateTag(tag.children.iterator(), HtmlTag.class,
				HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(HtmlTag tag) {
		ValidateTag(tag.children.iterator(), BodyTag.class, HeadTag.class,
				HtmlWhitespace.class);
	}

	@Override
	public void Visit(HtmlString tag) {
		// all strings are valid
	}

	@Override
	public void Visit(HtmlWhitespace tag) {
		// all whitespace is valid
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(HeadTag tag) {
		ValidateTag(tag.children.iterator(), TitleTag.class,
				HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(BodyTag tag) {
		ValidateTag(tag.children.iterator(), HtmlWhitespace.class,
				HtmlString.class, BoldTag.class, EmphasisTag.class,
				H1Tag.class, H2Tag.class, H3Tag.class, H4Tag.class,
				H5Tag.class, H6Tag.class, DlTag.class, OlTag.class,
				UlTag.class, TableTag.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(TitleTag tag) {
		ValidateTag(tag.children.iterator(), HtmlString.class,
				HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(BoldTag tag) {
		ValidateTag(tag.children.iterator(), HtmlString.class,
				HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(EmphasisTag tag) {
		ValidateTag(tag.children.iterator(), HtmlString.class,
				HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(TableTag tag) {
		ValidateTag(tag.children.iterator(), TrTag.class, HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(TrTag tag) {
		ValidateTag(tag.children.iterator(), TdTag.class, HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(TdTag tag) {
		ValidateTag(tag.children.iterator(), HtmlString.class,
				HtmlWhitespace.class, BoldTag.class, EmphasisTag.class,
				TableTag.class, OlTag.class, UlTag.class, DlTag.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(H1Tag tag) {
		ValidateTag(tag.children.iterator(), HtmlString.class,
				HtmlWhitespace.class);
		;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(H2Tag tag) {
		ValidateTag(tag.children.iterator(), HtmlString.class,
				HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(H3Tag tag) {
		ValidateTag(tag.children.iterator(), HtmlString.class,
				HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(H4Tag tag) {
		ValidateTag(tag.children.iterator(), HtmlString.class,
				HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(H5Tag tag) {
		ValidateTag(tag.children.iterator(), HtmlString.class,
				HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(H6Tag tag) {
		ValidateTag(tag.children.iterator(), HtmlString.class,
				HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(OlTag tag) {
		ValidateTag(tag.children.iterator(), LiTag.class, HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(UlTag tag) {
		ValidateTag(tag.children.iterator(), LiTag.class, HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(LiTag tag) {
		ValidateTag(tag.children.iterator(), HtmlWhitespace.class,
				HtmlString.class, BoldTag.class, EmphasisTag.class,
				TableTag.class, OlTag.class, UlTag.class, DlTag.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(DlTag tag) {
		ValidateTag(tag.children.iterator(), DtTag.class, HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(DtTag tag) {
		ValidateTag(tag.children.iterator(), DdTag.class, HtmlWhitespace.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Visit(DdTag tag) {
		ValidateTag(tag.children.iterator(), HtmlWhitespace.class,
				HtmlString.class, BoldTag.class, EmphasisTag.class,
				TableTag.class, OlTag.class, UlTag.class, DlTag.class);
	}
}
