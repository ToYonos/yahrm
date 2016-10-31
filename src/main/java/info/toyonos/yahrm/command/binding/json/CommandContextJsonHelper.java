package info.toyonos.yahrm.command.binding.json;

import info.toyonos.yahrm.command.Command;
import info.toyonos.yahrm.command.CommandContext;
import info.toyonos.yahrm.command.CommandFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommandContextJsonHelper
{
	@Autowired
	private CommandFactory commandFactory;
	protected ObjectMapper objectMapper;
	
	public CommandContextJsonHelper()
	{
		objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule(getClass().getSimpleName(), new Version(1, 0, 0, null));
		module.addSerializer(CommandContext.class, new CommandContextSerializer());
		module.addDeserializer(CommandContext.class, new CommandContextDeserializer());
		objectMapper.registerModule(module);
	}
	
	private class CommandContextSerializer extends JsonSerializer<CommandContext>
	{
		@Override
		public void serialize(CommandContext cmdCtx, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException
		{
			if (cmdCtx.getParameters().length > 0)
			{
				jgen.writeStartObject();
				jgen.writeStringField(
					StringUtils.substringBefore(cmdCtx.getCommand().getClass().getSimpleName(), "$"),
					StringUtils.stripEnd(Arrays.toString(cmdCtx.getParameters()).substring(1), "]"));
				jgen.writeEndObject();
			}
			else
			{
				jgen.writeString(cmdCtx.getCommand().getClass().getSimpleName().substring(0, cmdCtx.getCommand().getClass().getSimpleName().indexOf("$")));				
			}
		}
	}
	
	private class CommandContextDeserializer extends JsonDeserializer<CommandContext>
	{		
		@Override
		public CommandContext deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException
		{
			try
			{
				JsonNode node = jp.getCodec().readTree(jp);
				if (node.isTextual())
				{
					return new CommandContext((Command) getNewCommandMethod(node.getTextValue()).invoke(commandFactory));
				}
				else
				{
					Entry<String, JsonNode> objectNode = node.getFields().next();
					return new CommandContext(
						(Command) getNewCommandMethod(objectNode.getKey()).invoke(commandFactory),
						(Object[]) objectNode.getValue().getTextValue().trim().split("\\s*,\\s*")
					);
				}
			}
			catch (ReflectiveOperationException | SecurityException e)
			{
				throw new JsonParseException(e.getMessage(), null, e);
			}
		}
		
		private Method getNewCommandMethod(String command) throws JsonParseException, SecurityException
		{
			try
			{
				return commandFactory.getClass().getMethod("new" + command);
			}
			catch (NoSuchMethodException e)
			{
				throw new JsonParseException(String.format("Unknown command '%s'", command), null, e);
			}
		}
	}
}
