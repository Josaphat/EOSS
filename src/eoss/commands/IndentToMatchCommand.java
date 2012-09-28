package eoss.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eoss.gui.TabManager;

/**
 * The Insert command inserts texts into a given TabManager. The Text is given
 * at the time of instantiation of the command. It supports the insertion of
 * text over a selection.
 * 
 * @author
 * 
 */
public class IndentToMatchCommand implements ActionListener {
	private TabManager tabManager;

	/**
	 * Tabs the current line to match the indentation of the previous one
	 * 
	 * @param tabManager
	 *            the TabManager where text is to be inserted
	 * @param text
	 *            the string to insert
	 */
	public IndentToMatchCommand(TabManager tabManager) {
		this.tabManager = tabManager;
	}

	/**
	 * Replaces the current line with one with indentation matching the previous
	 * one.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int position = tabManager.GetCursorPosition();
		String text = tabManager.getText();

		/*
		 * int startOfCurLine = text.lastIndexOf('\n', position)+1; if
		 * (text.lastIndexOf('\t', position)>startOfCurLine)
		 * startOfCurLine=text.lastIndexOf('\t', position)+1; int startOfPreLine
		 * = text.lastIndexOf('\n', startOfCurLine); if (startOfPreLine==-1)
		 * startOfPreLine=0; SOP("start of this line:"+startOfCurLine);
		 * SOP("start of previous line:"+startOfPreLine); if
		 * (startOfCurLine==-1||startOfCurLine<0) return;//can't do it
		 * SOP("MOVING ON"); //now count the tabs boolean stop = false; int
		 * tabCount=0; for (int i=startOfPreLine+1;!stop&&i<startOfCurLine;i++){
		 * if (text.charAt(i)=='\t') tabCount++; else stop=true; }
		 * SOP("tab count:"+tabCount); String beforeThisLine = text.substring(0,
		 * startOfCurLine); String thisLine = text.substring(startOfCurLine);
		 * SOP("start of this:"+startOfCurLine); SOP("this line1:"+thisLine);
		 * int endOfThisLine=thisLine.indexOf('\n');
		 * 
		 * String theEnd= ""; if (endOfThisLine==-1)
		 * endOfThisLine=thisLine.length(); else theEnd =
		 * thisLine.substring(endOfThisLine);
		 * SOP("end of this line:"+endOfThisLine);
		 * thisLine=thisLine.substring(0,endOfThisLine);
		 * SOP("this line2:"+thisLine);
		 * //thisLine=thisLine.substring(thisLine.lastIndexOf
		 * ('\t',thisLine.length()-1)); for (int t =0; t<tabCount;t++)
		 * thisLine='\t'+thisLine; SOP("this line:"+thisLine);
		 * textArea.setText(beforeThisLine+thisLine+theEnd);
		 */

		int myTabCount = 0;
		int previousTabCount = 0;
		int beginningOfMyLine;

		beginningOfMyLine = text.lastIndexOf('\n', position);// look back to the
																// last newline
		if (beginningOfMyLine <= 0)// there;s no previous line to match
			return;
		// count this line's tabs
		boolean stop = false;
		for (int p = beginningOfMyLine + 1; !stop && p < text.length(); p++) {
			if (text.charAt(p) == '\t')
				myTabCount++;
			else
				stop = true;// something other than a tab, stop counting
		}

		int beginningOfPreviousLine = text.lastIndexOf('\n',
				beginningOfMyLine - 1);
		// if (beginningOfPreviousLine==-1)//if none, then it's the top of the
		// file
		// beginningOfPreviousLine=0;

		// count tabs on previous line
		stop = false;
		for (int p = beginningOfPreviousLine + 1; !stop
				&& p < beginningOfMyLine; p++) {
			if (text.charAt(p) == '\t')
				previousTabCount++;
			else
				stop = true;// something other than a tab, stop counting
		}

		StringBuilder out = new StringBuilder();
		out.append(text.substring(0, beginningOfMyLine) + '\n');
		for (int i = 0; i < previousTabCount; i++)
			out.append('\t');
		out.append(text.substring(beginningOfMyLine + 1 + myTabCount));

		tabManager.ReplaceAllText(out.toString());
		tabManager.SetCursorPosition(beginningOfMyLine + 1 + previousTabCount);

	}
}
