package info.toyonos.yahrm.command.binding;

import info.toyonos.yahrm.command.CommandContext;

public interface CommandContextDeserializer<T>
{
	CommandContext[] deserialize(T arg) throws CommandContextDeserializationException;
}
