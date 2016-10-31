package info.toyonos.yahrm.command;

import org.apache.commons.lang3.tuple.Pair;

public class CommandContext
{
	private Pair<Command, Object[]> command;
	
	public CommandContext(Command cmd, Object... params)
	{
		this.command = Pair.of(cmd, params);
	}
	
	public CommandContext(Command cmd)
	{
		this.command = Pair.of(cmd, new Object[0]);
	}
	
	public Command getCommand()
	{
		return command.getLeft();
	}
	
	public Object[] getParameters()
	{
		return command.getRight();
	}
}
