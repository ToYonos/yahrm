package info.toyonos.yahrm.common;

public class IllegalConversionException extends Exception
{
	private static final long serialVersionUID = 1L;

	public IllegalConversionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public IllegalConversionException(String message)
	{
		super(message);
	}

	public IllegalConversionException(Throwable cause)
	{
		super(cause);
	}
}