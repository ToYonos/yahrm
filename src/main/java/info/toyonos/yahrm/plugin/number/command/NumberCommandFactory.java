package info.toyonos.yahrm.plugin.number.command;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.basic.BasicCommandFactory;

public class NumberCommandFactory extends BasicCommandFactory
{
	public Command newBumpPlusCommand()
	{
		return create(BumpPlusCommand.class);
	}

	public Command newBumpMinusCommand()
	{
		return create(BumpMinusCommand.class);
	}

	public Command newAddCommand()
	{
		return create(AddCommand.class);
	}
	
	public Command newSubCommand()
	{
		return create(SubCommand.class);
	}
	
	public Command newJumpIfZeroCommand()
	{
		return create(JumpIfZeroCommand.class);
	}

	public Command newJumpIfNegativeCommand()
	{
		return create(JumpIfNegativeCommand.class);
	}
}