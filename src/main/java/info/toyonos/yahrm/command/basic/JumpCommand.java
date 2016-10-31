package info.toyonos.yahrm.command.basic;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.SingleParameterCommand;
import info.toyonos.yahrm.outcome.Outcome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class JumpCommand extends SingleParameterCommand<Integer> implements Command
{
	public JumpCommand()
	{
		super(Integer.class);
	}
	
	@Override
	public Outcome execute(Integer where)
	{
		return getOutcomeFactory().newJumpOutcome(where);
	}
}