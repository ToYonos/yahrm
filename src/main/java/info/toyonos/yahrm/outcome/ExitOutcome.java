package info.toyonos.yahrm.outcome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.Runner;

@Component
@Scope("prototype")
public class ExitOutcome implements Outcome
{
	private Runner runner;

	ExitOutcome(Runner runner)
	{
		this.runner = runner;
	}

	@Override
	public void face()
	{
		runner.jump(-1);
	}
}
