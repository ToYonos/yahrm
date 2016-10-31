package info.toyonos.yahrm.command.binding;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.CommandContext;
import info.toyonos.yahrm.command.CommandFactory;

@Component
@Scope("prototype")
public class CommandContextTextDeserializer implements CommandContextDeserializer<String>
{
	@Autowired
	private CommandFactory commandFactory;
	
	@Override
	public CommandContext[] deserialize(String input) throws CommandContextDeserializationException
	{
		if (input == null) throw new IllegalArgumentException("The input cannot be null");
		try
		{
			StringTokenizer st = new StringTokenizer(input, "\r\n");
			CommandContext[] cmdCtxs = new CommandContext[st.countTokens()];
			int i = 0;
			while (st.hasMoreTokens())
			{
				String[] el = st.nextToken().trim().split("\\s+");
				cmdCtxs[i++] = new CommandContext((Command) getNewCommandMethod(el[0]).invoke(commandFactory), (Object[]) Arrays.copyOfRange(el, 1, el.length));
				
			}
			return cmdCtxs;
		}
		catch (ReflectiveOperationException e)
		{
			throw new CommandContextDeserializationException(e);
		}
	}
	
	private Method getNewCommandMethod(String command) throws NoSuchMethodException
	{
		return commandFactory.getClass().getMethod("new" + command + "Command");
	}
}
