package info.toyonos.yahrm.io;

import java.util.ArrayList;
import java.util.Collection;

public class AssertCollectionOutputReceiver extends AssertOutputReceiver
{
	private Collection<String> output;

	public AssertCollectionOutputReceiver(Object... values)
	{
		super(values);
		this.output = new ArrayList<>();
	}

	public AssertCollectionOutputReceiver(Collection<String> output, Object... values)
	{
		super(values);
		this.output = output;
	}
	
	public void onOk(String msg)
	{
		output.add(msg);
	}
	
	public void onKo(String msg)
	{
		output.add(msg);
	}
}