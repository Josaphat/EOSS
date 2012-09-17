import java.util.ArrayList;
import java.util.Arrays;

/**
 * TODO: docs
 *
 */
public class HtmlEditor {
	public static void main(String[] args){
		// TODO
		String[] constructs = {
				"b", "i",
				"table", "tr", "td",
				"h1", "h2", "h3", "h4", "h5", "h6",
				"ol", "ul", "li",
				"dl", "dt", "dd",
		};
		new gui.EditorWindow(new ArrayList<String>(Arrays.asList(constructs)));
	}
}