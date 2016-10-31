package info.toyonos.yahrm.command.binding.json;
import info.toyonos.yahrm.command.CommandContext;
import info.toyonos.yahrm.command.binding.CommandContextSerializationException;
import info.toyonos.yahrm.command.binding.CommandContextSerializer;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.util.DefaultPrettyPrinter;
import org.codehaus.jackson.util.DefaultPrettyPrinter.Lf2SpacesIndenter;
import org.codehaus.jackson.util.DefaultPrettyPrinter.NopIndenter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CommandContextJsonSerializer extends CommandContextJsonHelper implements CommandContextSerializer<File>
{
	@Override
	public void serialize(File output, CommandContext[] commands) throws CommandContextSerializationException
	{
		if (output == null) throw new IllegalArgumentException("The ouput file cannot be null");
		try
		{
			DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
			pp.indentArraysWith(new Lf2SpacesIndenter());
			pp.indentObjectsWith(new NopIndenter());
			objectMapper.writer(pp).writeValue(output, commands);
		}
		catch (IOException e)
		{
			throw new CommandContextSerializationException(e);
		}
	}
}
