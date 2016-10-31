package info.toyonos.yahrm.plugin.number.command;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AddCommand extends OperationCommand
{
	@Override
	public int operation(int v1, int v2)
	{
		return v1 + v2;
	}

}
