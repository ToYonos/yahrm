package info.toyonos.yahrm.plugin.number.command;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BumpPlusCommand extends BumpCommand
{
	@Override
	public int operation(int data)
	{
		return data + 1;
	}
}
