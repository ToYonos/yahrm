package info.toyonos.yahrm.common;

public class FatalErrorException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public FatalErrorException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public FatalErrorException(String message)
	{
		super(message);
	}

	public FatalErrorException(Throwable cause)
	{
		super(cause);
	}
}