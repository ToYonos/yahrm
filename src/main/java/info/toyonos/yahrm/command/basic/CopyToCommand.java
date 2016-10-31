package info.toyonos.yahrm.command.basic;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.SingleParameterCommand;
import info.toyonos.yahrm.outcome.Outcome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CopyToCommand extends SingleParameterCommand<Integer> implements Command
{
	public CopyToCommand()
	{
		super(Integer.class);
	}
	
	@Override
	public Outcome execute(Integer pos)
	{
		Object data = getBuffer().read();
		if (data != null)
		{
			getDataSpace().write(pos, data);
			return getOutcomeFactory().newEmptyOutcome();
		}
		else
		{
			return getOutcomeFactory().newErrorOutcome("Unable to run the command : the buffer is empty");
		}
	}
}
