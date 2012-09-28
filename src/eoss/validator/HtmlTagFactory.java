package eoss.validator;

/**
 * Factory method class for creating HTML tag objects.
 * 
 * @author Thomas Kohlman
 * 
 */
public class HtmlTagFactory {
	/**
	 * Factory method: create a different HTML tag based on the opening/closing
	 * text.
	 * 
	 * @param start
	 *            - opening tag text
	 * @param end
	 *            - closing tag text
	 * @return
	 */
	public ITag Create(String start, String end) {
		String tag = start.replaceAll("\\s+$", "");

		// If the open and closing tags do not match, return null
		if (!tag.equals(end.substring(1).replaceAll("\\s+$", ""))) {
			return null;
		}

		if (tag.equals("html")) {
			return new HtmlTag(start, end);
		} else if (tag.equals("head")) {
			return new HeadTag(start, end);
		} else if (tag.equals("body")) {
			return new BodyTag(start, end);
		} else if (tag.equals("title")) {
			return new TitleTag(start, end);
		} else if (tag.equals("b")) {
			return new BoldTag(start, end);
		} else if (tag.equals("i")) {
			return new EmphasisTag(start, end);
		} else if (tag.equals("table")) {
			return new TableTag(start, end);
		} else if (tag.equals("tr")) {
			return new TrTag(start, end);
		} else if (tag.equals("td")) {
			return new TdTag(start, end);
		} else if (tag.equals("h1")) {
			return new H1Tag(start, end);
		} else if (tag.equals("h2")) {
			return new H2Tag(start, end);
		} else if (tag.equals("h3")) {
			return new H3Tag(start, end);
		} else if (tag.equals("h4")) {
			return new H4Tag(start, end);
		} else if (tag.equals("h5")) {
			return new H5Tag(start, end);
		} else if (tag.equals("h6")) {
			return new H6Tag(start, end);
		} else if (tag.equals("ol")) {
			return new OlTag(start, end);
		} else if (tag.equals("ul")) {
			return new UlTag(start, end);
		} else if (tag.equals("li")) {
			return new LiTag(start, end);
		} else if (tag.equals("dl")) {
			return new DlTag(start, end);
		} else if (tag.equals("dt")) {
			return new DtTag(start, end);
		} else if (tag.equals("dd")) {
			return new DdTag(start, end);
		} else {
			return null;
		}
	}
}
