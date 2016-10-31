package info.toyonos.yahrm;

import java.util.List;

import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import info.toyonos.yahrm.command.CommandContext;
import info.toyonos.yahrm.command.CommandFactory;
import info.toyonos.yahrm.command.binding.CommandContextDeserializationException;
import info.toyonos.yahrm.command.binding.CommandContextDeserializer;
import info.toyonos.yahrm.command.binding.CommandContextSerializationException;
import info.toyonos.yahrm.command.binding.CommandContextSerializer;
import info.toyonos.yahrm.common.BeanDef;
import info.toyonos.yahrm.io.InputProvider;
import info.toyonos.yahrm.io.OutputReceiver;

public class Program
{
	private AnnotationConfigApplicationContext context;
	private CommandContext[] commands;

	public Program()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("info.toyonos.yahrm.configuration");
		context.refresh();
	}

	public void registerInputProvider(Class<? extends InputProvider> inputProviderClass)
	{
		registerInputProvider(inputProviderClass, null);
	}
	
	public void registerInputProvider(Class<? extends InputProvider> inputProviderClass, List<Object> params)
	{
		registerBean(InputProvider.class.getSimpleName(), inputProviderClass, params);
	}
	
	public void registerOutputReceiver(Class<? extends OutputReceiver> outputReceiverClass)
	{
		registerOutputReceiver(outputReceiverClass, null);
	}
	
	public void registerOutputReceiver(Class<? extends OutputReceiver> outputReceiverClass, List<Object> params)
	{
		registerBean(OutputReceiver.class.getSimpleName(), outputReceiverClass, params);
	}

	public <T extends CommandFactory> T registerCommandFactory(Class<T> commandFactoryClass)
	{
		registerBean(CommandFactory.class.getSimpleName(), commandFactoryClass, null);
		return context.getBean(commandFactoryClass);
	}
	
	public <T> void run(Class<? extends CommandContextDeserializer<T>> deserializerClass, T arg) throws CommandContextDeserializationException
	{
		run(context.getBean(deserializerClass).deserialize(arg));
	}

	public void run(CommandContext... commands)
	{
		this.commands = commands;
		context.getBean(StandardRunner.class).run(commands);
		context.getBean(OutputReceiver.class).onEnd();
	}
	
	public <T> void export(Class<? extends CommandContextSerializer<T>> serializerClass, T arg) throws CommandContextSerializationException
	{
		if (commands != null)
		{
			context.getBean(serializerClass).serialize(arg, commands);
		}
	}
	
	private void registerBean(String name, Class<?> beanClass, List<Object> values)
	{
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(beanClass);
		beanDefinition.setLazyInit(false);
		beanDefinition.setAbstract(false);
		if (values != null)
		{
			ConstructorArgumentValues cav = new ConstructorArgumentValues();
			for (Object value : values)
			{
				cav.addGenericArgumentValue(value instanceof Object[] ? transformArrayWithSpringBeans((Object[]) value) : value);
			}
			beanDefinition.setConstructorArgumentValues(cav);
		}
		beanDefinition.setAutowireCandidate(true);
		beanDefinition.setScope("singleton");

		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) context.getAutowireCapableBeanFactory();
		registry.registerBeanDefinition(name, beanDefinition);		
	}
	
	// TODO with Collection to ?
	// TODO refactoring
	private Object[] transformArrayWithSpringBeans(Object[] input)
	{
		Object[] output = new Object[input.length];
		for (int i = 0; i < input.length; i++)
		{
			if (input[i] instanceof Class<?>)
			{
				Object bean = context.getBean((Class<?>) input[i]);
				if (bean != null)
				{
					output[i] = bean;
					continue;
				}
			}
			else if (input[i] instanceof BeanDef)
			{
				BeanDef<?,?> beanDef = (BeanDef<?,?>) input[i];
				Object bean = context.getBean((Class<?>) beanDef.getType(), beanDef.getArgs());
				if (bean != null)
				{
					output[i] = bean;
					continue;
				}
			}
			System.arraycopy(input, i, output, i, 1);
		}
		return output;
	}
}