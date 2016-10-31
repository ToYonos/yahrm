package info.toyonos.yahrm.command.binding.json;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.command.CommandContext;
import info.toyonos.yahrm.command.binding.CommandContextDeserializationException;
import info.toyonos.yahrm.command.binding.CommandContextDeserializer;

@Component
@Scope("prototype")
public class CommandContextJsonDeserializer extends CommandContextJsonHelper implements CommandContextDeserializer<InputStream>
{
	@Override
	public CommandContext[] deserialize(InputStream input) throws CommandContextDeserializationException
	{
		if (input == null) throw new IllegalArgumentException("The input cannot be null");
		try
		{
			return objectMapper.readValue(input, CommandContext[].class);
		}
		catch (IOException e)
		{
			throw new CommandContextDeserializationException(e);
		}
	}
}