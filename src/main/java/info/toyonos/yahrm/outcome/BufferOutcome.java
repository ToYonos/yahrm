package info.toyonos.yahrm.outcome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.common.Context;

@Component
@Scope("prototype")
public class BufferOutcome extends Context implements Outcome
{
	private Object what;

	BufferOutcome(Object what)
	{
		this.what = what;
	}

	@Override
	public void face()
	{
		getBuffer().write(what); 
	}
}
