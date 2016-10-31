package info.toyonos.yahrm.outcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.Runner;

@Component
public class OutcomeFactory implements ApplicationContextAware
{
	private ApplicationContext context;
	private Runner runner;

	@Autowired
	public OutcomeFactory(Runner runner)
	{
		this.runner = runner;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
	{
		this.context = context;
	}

	public Outcome newBufferOutcome(Object what)
	{
		return context.getBean(BufferOutcome.class, what);
	}
	
	public Outcome newErrorOutcome(String msg)
	{
		return context.getBean(ErrorOutcome.class, msg);
	}
	
	public Outcome newJumpOutcome(int where)
	{
		return context.getBean(JumpOutcome.class, runner, where);
	}
	
	public Outcome newExitOutcome()
	{
		return context.getBean(ExitOutcome.class, runner);
	}
	
	public Outcome newEmptyOutcome()
	{
		return context.getBean(EmptyOutcome.class);
	}
}