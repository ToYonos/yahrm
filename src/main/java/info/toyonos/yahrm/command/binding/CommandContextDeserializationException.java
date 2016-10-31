package info.toyonos.yahrm.command.binding;

public class CommandContextDeserializationException extends Exception
{
	private static final long serialVersionUID = 4141967048337369362L;

	public CommandContextDeserializationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public CommandContextDeserializationException(String message)
	{
		super(message);
	}

	public CommandContextDeserializationException(Throwable cause)
	{
		super(cause);
	}
}
