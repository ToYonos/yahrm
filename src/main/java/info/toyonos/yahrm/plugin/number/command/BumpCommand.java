package info.toyonos.yahrm.plugin.number.command;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.SingleParameterCommand;
import info.toyonos.yahrm.common.IllegalConversionException;
import info.toyonos.yahrm.outcome.Outcome;

public abstract class BumpCommand extends SingleParameterCommand<Integer> implements Command
{	
	public BumpCommand()
	{
		super(Integer.class);
	}

	@Override
	public Outcome execute(Integer pos) throws IllegalConversionException
	{
		Integer data = getDataSpace().readInt(pos);
		if (data != null)
		{
			data = operation(data);
			getDataSpace().write(pos, data);
			return getOutcomeFactory().newBufferOutcome(data);
		}
		else
		{
			return getOutcomeFactory().newErrorOutcome("Unable to run the command : no value at pos " + pos);
		}
	}

	public abstract int operation(int data);
}