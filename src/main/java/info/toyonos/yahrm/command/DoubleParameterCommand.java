package info.toyonos.yahrm.command;

import info.toyonos.yahrm.common.Context;
import info.toyonos.yahrm.common.IllegalConversionException;
import info.toyonos.yahrm.common.ParameterUtils;
import info.toyonos.yahrm.outcome.Outcome;

import org.apache.commons.lang3.tuple.Pair;

public abstract class DoubleParameterCommand<A, B> extends Context implements Command
{
	private Class<A> class1;
	private Class<B> class2;
	
	public DoubleParameterCommand(Class<A> class1, Class<B> class2)
	{
		this.class1 = class1;
		this.class2 = class2;
	}

	@Override
	public Outcome execute(Object... parameter)
	{
		try
		{
			Pair<A, B> typedParams = ParameterUtils.extractParameter(class1, class2, parameter);
			return execute(typedParams.getLeft(), typedParams.getRight());
		}
		catch (IllegalConversionException e)
		{
			return getOutcomeFactory().newErrorOutcome("Unable to run the command : " + e.getMessage());
		}
	}

	public abstract Outcome execute(A p1, B p2) throws IllegalConversionException;
}
