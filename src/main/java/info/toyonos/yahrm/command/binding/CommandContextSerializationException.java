package info.toyonos.yahrm.command.binding;

public class CommandContextSerializationException extends Exception
{
	private static final long serialVersionUID = 4141967048337369362L;

	public CommandContextSerializationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public CommandContextSerializationException(String message)
	{
		super(message);
	}

	public CommandContextSerializationException(Throwable cause)
	{
		super(cause);
	}
}
