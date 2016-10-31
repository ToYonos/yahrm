package info.toyonos.yahrm.command;

import info.toyonos.yahrm.common.LoggerAdvice;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class CommandFactory implements ApplicationContextAware
{
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context)
	{
		this.context = context;
	}

	public Command create(Class<? extends Command> commandClass)
	{
		return addLoggerAspect(context.getBean(commandClass));
	}

	private Command addLoggerAspect(Command command)
	{
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new LoggerAdvice());
		pf.setTarget(command);
		return (Command) pf.getProxy();
	}
}
