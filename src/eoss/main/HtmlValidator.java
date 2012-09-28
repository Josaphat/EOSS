package eoss.main;

import eoss.validator.HtmlParseException;
import eoss.validator.HtmlTagFactory;
import eoss.validator.HtmlTextFactory;
import eoss.validator.ITag;
import eoss.validator.NotImplementedException;
import eoss.validator.RootTag;
import eoss.validator.ValidationVisitor;

/**
 * HtmlValidator initiates validation of an HTML file.
 * 
 * @author Thomas Kohlman
 */
public class HtmlValidator {

	private HtmlTagFactory factory;
	private HtmlTextFactory textFactory;

	private static final String START_TAG_START = "<";
	private static final String END_TAG_START = "</";
	private static final String TAG_END = ">";

	/**
	 * Constructor
	 */
	public HtmlValidator() {
		this.factory = new HtmlTagFactory();
		this.textFactory = new HtmlTextFactory();
	}

	/**
	 * Parse an HTML file.
	 * 
	 * @param buffer
	 *            - buffer containing HTML file text
	 * @return true if file is valid HTML, false otherwise
	 */
	public boolean validate(StringBuffer buffer) {
		ITag root = new RootTag();

		try {
			ParseHtml(buffer, root);
		} catch (HtmlParseException e) {
			/*
			 * Debugging output off. System.out.println(e.getMessage());
			 * e.printStackTrace();
			 */
			return false;
		}

		ValidationVisitor visitor = new ValidationVisitor();
		root.Accept(visitor);
		System.out.println("Validation result: " + visitor.IsValid());
		return visitor.IsValid();
	}

	/**
	 * Build the HTML composite tree.
	 * 
	 * @param buffer
	 *            - File text from which to build the tree.
	 * @param root
	 *            - Root node of the tree.
	 * @throws HtmlParseException
	 */
	private void ParseHtml(StringBuffer buffer, ITag root)
			throws HtmlParseException {
		int startIndex = 0;

		// Iterate as long as there are children
		while (true) {
			int startTagStartIndex = 0;
			int startTagEndIndex = 0;
			int endTagStartIndex = 0;
			int endTagEndIndex = 0;

			// Find the beginning of the next start tag ------------------------
			startTagStartIndex = buffer.indexOf(START_TAG_START, startIndex);

			// This is the base case.
			if (startTagStartIndex < 0) {
				AddTextElement(buffer.substring(startIndex), root);
				return;
			}

			// Find the end of the next start tag ------------------------------
			startTagEndIndex = buffer.indexOf(TAG_END, startTagStartIndex);
			if (startTagEndIndex < 0) {
				throw new HtmlParseException("No '>' found for opening tag.");
			}

			String startTag = buffer.substring(startTagStartIndex + 1,
					startTagEndIndex);
			String tagname = startTag.replaceAll("\\s+$", "");

			// Find the beginning of the corresponding end tag -----------------
			endTagStartIndex = buffer.indexOf(END_TAG_START + startTag.trim(),
					startTagEndIndex);
			if (endTagStartIndex < 0) {
				throw new HtmlParseException("No '</' found for closing tag.");
			}

			// Find the corresponding end tag ----------------------------------
			endTagEndIndex = buffer.indexOf(TAG_END, endTagStartIndex);
			if (endTagEndIndex < 0) {
				throw new HtmlParseException("No '>' found for closing tag.");
			}

			String endTag = buffer.substring(endTagStartIndex + 1,
					endTagEndIndex);

			// See if there is a string before this tag.
			if (startIndex != startTagStartIndex) {
				String str = buffer.substring(startIndex, startTagStartIndex);
				AddTextElement(str, root);
			}

			// Create the tag
			ITag tag = (ITag) factory.Create(startTag, endTag);
			if (tag == null) {
				throw new HtmlParseException("Invalid HTML tag: '" + tagname
						+ "'");
			}

			try {
				root.Add(tag);
			} catch (NotImplementedException e) {
				e.printStackTrace();
			}

			// Make the recursive parsing call to parse below the tag
			String tagstring = buffer.substring(startTagEndIndex + 1,
					endTagStartIndex);
			StringBuffer subbuffer = new StringBuffer(tagstring);
			ParseHtml(subbuffer, tag);

			// Set up the starting index for the iteration
			startIndex = endTagEndIndex + 1;
		}
	}

	/**
	 * Add a text object to the tree (leaf). May either be pure whitespace or
	 * may contain characters.
	 */
	private void AddTextElement(String str, ITag tag) {
		try {
			if (!str.isEmpty()) {
				tag.Add(textFactory.Create(str));
			}
		} catch (NotImplementedException e) {
			e.printStackTrace();
		}
	}
}
