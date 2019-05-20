package gdb;

public class GDBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GDBException(String st, Exception e) {
		super(st, e);
	}
}
