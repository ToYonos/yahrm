package info.toyonos.yahrm.outcome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.Runner;

@Component
@Scope("prototype")
public class JumpOutcome implements Outcome
{
	private Runner runner;
	private int where;

	JumpOutcome(Runner runner, int where)
	{
		this.runner = runner;
		this.where = where;
	}

	@Override
	public void face()
	{
		runner.jump(where);
	}
}
