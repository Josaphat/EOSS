package eoss.main;

import java.io.File;

/**
 * A FileBuffer keeps track of the data that is going to be saved to the disk,
 * as well as where that data is to be saved.
 * 
 * @author Josaphat Valdivia
 * 
 */
public class FileBuffer {

	private StringBuffer data;
	private File file;
	private boolean isSaved;

	/**
	 * Constructs a FileBuffer containing the given data and which is to be
	 * saved at the location of the given File object
	 * 
	 * @param data
	 *            the data initially contained within this buffer.
	 * @param file
	 *            the location where the data is to be saved.
	 */
	public FileBuffer(String data, File file) {
		this.data = new StringBuffer(data);
		this.file = file;
	}

	/**
	 * @return the data as a StringBuffer
	 */
	public StringBuffer getData() {
		return data;
	}

	/**
	 * Overwrites the data currently in this buffer with the data in the given
	 * String
	 * 
	 * @param data
	 *            the string to overwrite the current contents of this buffer
	 *            with
	 */
	public void setData(String data) {
		this.data = new StringBuffer(data);
	}

	/**
	 * Overwrites the data currently in this buffer with the data in the given
	 * StringBuffer
	 * 
	 * @param data
	 *            the StringBuffer to overwrite the current contents of this
	 *            buffer with.
	 */
	public void setData(StringBuffer data) {
		this.data = data;
	}

	/**
	 * Convenience method for getting the name of this file.
	 * 
	 * @return the name of the file including extension.
	 */
	public String getName() {
		return this.file.getName();
	}

	/**
	 * Internally returns the contents of the StringBuffer as plain String
	 */
	@Override
	public String toString() {
		return data.toString();
	}

	/**
	 * Gets the File object associated buffer. The File object holds the save
	 * location of the buffer.
	 * 
	 * @return the file where this object is saved.
	 */
	public File getFile() {
		return this.file;
	}

	/**
	 * Returns whether or not this FileBuffer has been saved at the location it
	 * contains.
	 * 
	 * @return true if the FileBuffer is saved, false otherwise
	 */
	public boolean isSaved() {
		return this.isSaved;
	}

	/**
	 * Returns the text between the given start and end indexes
	 * 
	 * @param start
	 *            the beginning of the selection
	 * @param end
	 *            the end of the selection
	 * @return a String with the contents of the buffer between the two indexes.
	 */
	public String getSelectionText(int start, int end) {
		return data.substring(start, end);
	}

	/**
	 * Sets the File location where this FileBuffer is to be stored.
	 * 
	 * @param file
	 *            the new location of the File on the disk
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Sets whether or not the contents of this FileBuffer have been saved on
	 * the disk.
	 * 
	 * @param b
	 *            true if the contents have been saved, false otherwise.
	 */
	public void setSaved(boolean b) {
		this.isSaved = b;
	}
}