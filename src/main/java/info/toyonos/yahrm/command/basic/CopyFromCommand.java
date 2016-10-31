package info.toyonos.yahrm.command.basic;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.SingleParameterCommand;
import info.toyonos.yahrm.outcome.Outcome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CopyFromCommand extends SingleParameterCommand<Integer> implements Command
{
	public CopyFromCommand()
	{
		super(Integer.class);
	}
	
	@Override
	public Outcome execute(Integer pos)
	{
		Object data = getDataSpace().read(pos);
		return getOutcomeFactory().newBufferOutcome(data);
	}
}
