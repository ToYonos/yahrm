package info.toyonos.yahrm.outcome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.common.FatalErrorException;

@Component
@Scope("prototype")
public class ErrorOutcome implements Outcome
{
	private String msg;

	ErrorOutcome(String msg)
	{
		this.msg = msg;
	}

	@Override
	public void face()
	{
		throw new FatalErrorException("⚠ " + msg);
	}
}