package info.toyonos.yahrm.command.binding;

import info.toyonos.yahrm.command.CommandContext;

public interface CommandContextSerializer<T>
{
	void serialize(T output, CommandContext[] commands) throws CommandContextSerializationException;
}
