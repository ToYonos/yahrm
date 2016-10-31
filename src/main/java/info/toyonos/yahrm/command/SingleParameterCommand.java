package info.toyonos.yahrm.command;

import info.toyonos.yahrm.common.Context;
import info.toyonos.yahrm.common.IllegalConversionException;
import info.toyonos.yahrm.common.ParameterUtils;
import info.toyonos.yahrm.outcome.Outcome;

public abstract class SingleParameterCommand<A> extends Context implements Command
{
	private Class<A> clazz;
	
	public SingleParameterCommand(Class<A> clazz)
	{
		this.clazz = clazz;
	}
	
	@Override
	public Outcome execute(Object... parameter)
	{
		try
		{
			return execute(ParameterUtils.extractParameter(clazz, parameter));
		}
		catch (IllegalConversionException e)
		{
			return getOutcomeFactory().newErrorOutcome("Unable to run the command : " + e.getMessage());
		}
	}

	public abstract Outcome execute(A p1) throws IllegalConversionException;
}
