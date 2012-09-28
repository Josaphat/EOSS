package eoss.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The DocumentManager is responsible for managing the various files that are
 * being edited. It manages opening and saving files, in addition to keeping
 * track of the file currently being edited.
 * 
 * @author
 * 
 */
public class DocumentManager {

	private HtmlValidator validator;

	public DocumentManager() {
		this.buffers = new ArrayList<FileBuffer>();
		this.clipboard = new String();
		this.activeBuffer = null;

		this.validator = new HtmlValidator();
	}

	/* Collection of buffers */
	private ArrayList<FileBuffer> buffers;

	/* The buffer currently being edited */
	private FileBuffer activeBuffer;

	/* An information holder. Can be used to pass info between buffers. */

	@SuppressWarnings("unused")
	// TODO: Remove the clipboard.
	private String clipboard;

	/**
	 * Opens a file using the given validator and makes it the active file. If
	 * the given File object cannot be read or does not exist, a new buffer is
	 * created using the File object with the assumption that it will be saved
	 * at the location given by File in the future.
	 * 
	 * @param validator
	 *            Object to validate the file for correctness
	 * @param file
	 *            The file to be opened
	 * @throws IOException
	 */
	public String open(File file) throws IOException {
		FileBuffer fb = null;
		if ((file.exists()) && (file.canRead())) {
			String data = "";
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (br.ready()) {
				data += br.readLine() + "\n"; // readLine skips \n characters
			}
			fb = new FileBuffer(data, file);
			buffers.add(fb);
			br.close();
		} else {
			fb = new FileBuffer("", file);
			buffers.add(fb);
		}
		this.activeBuffer = fb;

		return activeBuffer.getData().toString();
	}

	/**
	 * Saves the FileBuffer currently being edited to whatever File it already
	 * has stored.
	 * 
	 * @param text
	 *            The updated text of the file
	 * @throws IOException
	 */
	public void save(String text) throws IOException {
		save(this.activeBuffer.getFile(), text);
	}

	/**
	 * Saves the FileBuffer currently being edited to the given File. This is
	 * conceptually equivalent to calling a "save as" method. It changes the
	 * file stored in the FileBuffer to this new file and marks it as being
	 * saved.
	 * 
	 * @param f
	 *            destination file
	 * @param text
	 *            updated text of the file
	 * @throws IOException
	 */
	public void save(File f, String text) throws IOException {
		this.activeBuffer.setData(text);
		BufferedWriter out = new BufferedWriter(new FileWriter(f));
		out.write(this.activeBuffer.getData().toString());
		out.close();
		this.activeBuffer.setFile(f);
		this.activeBuffer.setSaved(true);
	}

	/**
	 * Allows the client to check whether the buffer which is currently being
	 * edited is saved. As soon as a change is made to a document, this method
	 * will return false.
	 * 
	 * @return true if the buffer is saved, false otherwise.
	 */
	public boolean isSaved() {
		return this.activeBuffer.isSaved();
	}

	/**
	 * TODO: This method should be removed. A client should only be able to
	 * modify a currently active buffer. -- Josaphat Remove a buffer from being
	 * managed.
	 * 
	 * @param buffer
	 * @throws BufferNotOpenException
	 */
	public void close(FileBuffer buffer) throws BufferNotOpenException {
		int index = buffers.indexOf(buffer);
		if (index == -1) {
			throw new BufferNotOpenException();
		} else {
			buffers.remove(index);
		}
	}

	/**
	 * Closes the active buffer. If this is the last buffer, no active buffer
	 * will be set until open() is called. If there are more buffers, the last
	 * buffer in the array will be the new active buffer.
	 */
	public void close() {
		this.buffers.remove(this.activeBuffer);
		if (!buffers.isEmpty()) {
			this.activeBuffer = this.buffers.get(buffers.size() - 1);
		} else {
			this.activeBuffer = null;
		}
	}

	/**
	 * The copy method takes the take text in the current buffer between
	 * index_low and index_high and places it into a copy buffer.
	 * 
	 * @param index_low
	 * @param index_high
	 */
	public void copy(int start, int end) {
		this.clipboard = this.activeBuffer.getSelectionText(start, end);
	}

	/**
	 * Returns the file name plus extension of the currently active buffer.
	 * 
	 * @return String containing file name plus extension of the active buffer.
	 */
	public String getName() {
		return this.activeBuffer.getName();
	}

	/**
	 * Returns the text of the currently active buffer as a String.
	 * 
	 * @return String containing the text of the active buffer.
	 */
	public String getText() {
		return this.activeBuffer.getData().toString();
	}

	/**
	 * Sets the text of the current buffer to the given text and then calls
	 * validate
	 * 
	 * @param text
	 *            String containing the most up-to-date contents of the
	 * @return true if the file validates, false otherwise
	 */
	public boolean validate(String text) {
		// TODO:
		// This next line is a stop-gap intended to be removed when the entire
		// DocumentManager->FileBuffer structure is scrapped completely. For now
		// this needs to be done because the DocumentManager is responsible for
		// disk operations and the Buffers contain the File information.
		this.activeBuffer.setData(text);
		return validator.validate(this.activeBuffer.getData());
	}

	/*
	 * TODO: The following two methods should not exist. They are ill thought
	 * out convenience methods which are only used once in the entire
	 * application. The code that uses these methods could just as easily have
	 * been written using the other methods provided by this class' interface.
	 */
	public ArrayList<FileBuffer> getOpenBuffers() {
		return buffers;
	}

	public FileBuffer getActiveBuffer() {
		return activeBuffer;
	}

	/**
	 * Sets the selected buffer to the given index. Precondition: The index is
	 * not negative. Precondition: The index is at most one less than the number
	 * of open buffers.
	 * 
	 * @param selectedIndex
	 *            the index of the selected buffer.
	 */
	public void setSelectedBuffer(int index) {
		if (index < 0) {
			// Nothing is selected.
			// Will cause exceptions to be thrown when operations that should
			// not occur are attempted.
			this.activeBuffer = null;
		} else if (index < this.buffers.size()) {
			this.activeBuffer = this.buffers.get(index);
			System.out.println("Title of now active buffer: "
					+ this.activeBuffer.getName());
		} else {
			// Error. Index is out of range. Rather than print a stack trace,
			// show some succinct error output.
			System.err.println("The given selectedIndex is out of range: "
					+ index);
			System.err.println("The number of open buffers is "
					+ this.buffers.size());
		}
	}

	/**
	 * Checks whether the manager has no open buffers or not.
	 * 
	 * @return true if the Manager is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.buffers.isEmpty();
	}
}
