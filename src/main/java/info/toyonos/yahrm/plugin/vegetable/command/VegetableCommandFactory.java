package info.toyonos.yahrm.plugin.vegetable.command;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.plugin.number.command.NumberCommandFactory;


public class VegetableCommandFactory extends NumberCommandFactory
{
	public Command newWaterCommand()
	{
		return create(WaterCommand.class);
	}

	public Command newJumpIfVegetableCommand()
	{
		return create(JumpIfVegetableCommand.class);
	}
}