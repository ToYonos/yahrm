package info.toyonos.yahrm;

import info.toyonos.yahrm.command.CommandContext;

public interface Runner
{
	void run(CommandContext... commands);

	void jump(int where);
	
	//void runOne(); ?
}
