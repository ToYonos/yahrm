package info.toyonos.yahrm.command.basic;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.common.Context;
import info.toyonos.yahrm.outcome.Outcome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class InputCommand extends Context implements Command
{
	@Override
	public Outcome execute(Object... parameter)
	{
		Object data = getInputProvider().provide();
		return data != null ? getOutcomeFactory().newBufferOutcome(data) : getOutcomeFactory().newExitOutcome();
	}
}