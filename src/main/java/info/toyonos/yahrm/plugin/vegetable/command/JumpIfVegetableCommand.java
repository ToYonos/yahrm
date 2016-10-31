package info.toyonos.yahrm.plugin.vegetable.command;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.DoubleParameterCommand;
import info.toyonos.yahrm.common.IllegalConversionException;
import info.toyonos.yahrm.outcome.Outcome;
import info.toyonos.yahrm.plugin.vegetable.model.Vegetable;

@SuppressWarnings("rawtypes")
@Component
@Scope("prototype")
public class JumpIfVegetableCommand extends DoubleParameterCommand<Integer, Class> implements Command
{
	public JumpIfVegetableCommand()
	{
		super(Integer.class, Class.class);
	}

	@Override
	public Outcome execute(Integer where, Class type) throws IllegalConversionException
	{
		return type.isInstance(getBuffer().read(Vegetable.class)) ?
			getOutcomeFactory().newJumpOutcome(where) :
			getOutcomeFactory().newEmptyOutcome();
	}
}
