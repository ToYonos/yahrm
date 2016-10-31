package info.toyonos.yahrm.plugin.number.command;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.SingleParameterCommand;
import info.toyonos.yahrm.common.IllegalConversionException;
import info.toyonos.yahrm.outcome.Outcome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class JumpIfZeroCommand extends SingleParameterCommand<Integer> implements Command
{
	public JumpIfZeroCommand()
	{
		super(Integer.class);
	}
	
	@Override
	public Outcome execute(Integer where) throws IllegalConversionException
	{
		Integer data = getBuffer().readInt();
		return Integer.valueOf(0).equals(data) ? getOutcomeFactory().newJumpOutcome(where) : getOutcomeFactory().newEmptyOutcome();
	}
}