package info.toyonos.yahrm.plugin.vegetable.command;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.SingleParameterCommand;
import info.toyonos.yahrm.common.IllegalConversionException;
import info.toyonos.yahrm.outcome.Outcome;
import info.toyonos.yahrm.plugin.vegetable.model.Vegetable;

@Component
@Scope("prototype")
public class WaterCommand extends SingleParameterCommand<Integer> implements Command
{
	public WaterCommand()
	{
		super(Integer.class);
	}

	@Override
	public Outcome execute(Integer pos) throws IllegalConversionException
	{
		Vegetable veg = getDataSpace().read(pos, Vegetable.class);
		if (veg != null)
		{
			veg.water();
			return getOutcomeFactory().newBufferOutcome(veg);
		}
		else
		{
			return getOutcomeFactory().newErrorOutcome("Unable to run the command : no value at pos " + pos);
		}
	}
}