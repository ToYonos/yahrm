package info.toyonos.yahrm.command.basic;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.CommandFactory;


public class BasicCommandFactory extends CommandFactory
{	
	public Command newInputCommand()
	{
		return create(InputCommand.class);
	}

	public Command newOutputCommand()
	{
		return create(OutputCommand.class);
	}

	public Command newJumpCommand()
	{
		return create(JumpCommand.class);
	}

	public Command newCopyFromCommand()
	{
		return create(CopyFromCommand.class);
	}

	public Command newCopyToCommand()
	{
		return create(CopyToCommand.class);
	}
}
