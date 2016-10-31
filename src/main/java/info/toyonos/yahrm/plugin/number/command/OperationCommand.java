package info.toyonos.yahrm.plugin.number.command;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.SingleParameterCommand;
import info.toyonos.yahrm.common.IllegalConversionException;
import info.toyonos.yahrm.outcome.Outcome;

public abstract class OperationCommand extends SingleParameterCommand<Integer> implements Command
{	
	public OperationCommand()
	{
		super(Integer.class);
	}

	@Override
	public Outcome execute(Integer pos) throws IllegalConversionException
	{
		Integer v1 = getBuffer().readInt();
		if (v1 != null)
		{
			Integer v2 = getDataSpace().readInt(pos);
			if (v2 != null)
			{
				return getOutcomeFactory().newBufferOutcome(operation(v1, v2));
			}
			else
			{
				return getOutcomeFactory().newErrorOutcome("Unable to run the command : no value at pos " + pos);
			}
		}
		else
		{
			return getOutcomeFactory().newErrorOutcome("Unable to run the command : the buffer is empty");
		}
	}

	public abstract int operation(int v1, int v2);
}