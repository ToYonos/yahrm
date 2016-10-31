package info.toyonos.yahrm.command.basic;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.common.Context;
import info.toyonos.yahrm.outcome.Outcome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class OutputCommand extends Context implements Command
{
	@Override
	public Outcome execute(Object... parameter)
	{
		Object data = getBuffer().read();
		if (data != null)
		{
			getOutputReceiver().receive(data);
			return getOutcomeFactory().newBufferOutcome(null);
		}
		else
		{
			return getOutcomeFactory().newErrorOutcome("Unable to run the command : the buffer is empty");
		}
	}
}