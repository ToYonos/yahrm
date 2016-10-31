package info.toyonos.yahrm.command;

import info.toyonos.yahrm.outcome.Outcome;

public interface Command
{
	Outcome execute(Object... parameter);
}