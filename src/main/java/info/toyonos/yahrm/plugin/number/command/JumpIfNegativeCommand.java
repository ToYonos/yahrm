package info.toyonos.yahrm.plugin.number.command;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.SingleParameterCommand;
import info.toyonos.yahrm.common.IllegalConversionException;
import info.toyonos.yahrm.outcome.Outcome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class JumpIfNegativeCommand extends SingleParameterCommand<Integer> implements Command
{
	public JumpIfNegativeCommand()
	{
		super(Integer.class);
	}
	
	@Override
	public Outcome execute(Integer where) throws IllegalConversionException
	{
		Integer data = getBuffer().readInt();
		return data != null && data < 0 ? getOutcomeFactory().newJumpOutcome(where) : getOutcomeFactory().newEmptyOutcome();
	}
}