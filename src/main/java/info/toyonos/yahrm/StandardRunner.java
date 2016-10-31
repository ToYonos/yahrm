package info.toyonos.yahrm;

import info.toyonos.yahrm.command.CommandContext;

import org.springframework.stereotype.Component;

@Component
public class StandardRunner implements Runner
{
	private int cursor;

	public StandardRunner()
	{
		cursor = 0;
	}
	@Override
	public void run(CommandContext... commands)
	{
		while (cursor >= 0 && cursor < commands.length)
		{
			CommandContext ctx = commands[cursor++];
			ctx.getCommand().execute(ctx.getParameters()).face();
		}
	}

	@Override
	public void jump(int where)
	{
		this.cursor = where;
	}
}