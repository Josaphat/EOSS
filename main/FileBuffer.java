package main;

import java.util.ArrayList;

/**
 * FileBuffer contains the contents of a file
 * @author jxv1308
 *
 */
public class FileBuffer {
	private Validator validator;
	private ArrayList<String> lines;
	
	public FileBuffer(Validator validator) {
		this.validator = validator;
	}
	
	/**
	 * TODO: Docs
	 * @return true if the file is valid, false if it is not
	 */
	public boolean validate() {
		return validator.validate();
	}

	/**
	 * Adds the given line after the last line of the buffer.
	 * @param line String to append in a new line
	 */
	public void append(String line){
		this.lines.add(line);
	}
}