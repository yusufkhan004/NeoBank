package Exceptions;

public class InvalidAmountException extends Exception {

	private static final long serialVersionUID = 1L;
	public InvalidAmountException(String s)
	{
		super(s);
	}

}