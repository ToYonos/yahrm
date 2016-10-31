package info.toyonos.yahrm.io;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public abstract class AssertOutputReceiver implements OutputReceiver
{
	private Queue<Object> values;

	public AssertOutputReceiver(Object... values)
	{
		this.values = new LinkedList<>(Arrays.asList(values));
	}

	public AssertOutputReceiver(OutputStream output, Object... values)
	{
		this.values = new LinkedList<>(Arrays.asList(values));
	}

	protected Object poll()
	{
		return values.poll();
	}
	
	@Override
	public void receive(Object what)
	{
		Object expected = poll();
		if (!what.equals(expected))
		{
			onKo(String.format("✗ Unexpected value '%s', expected : '%s'", what, expected));
		}
		else
		{
			onOk(String.format("✓ Output : '%s', expected : '%s'", what, expected));
		}
	}
	
	@Override
	public void onEnd()
	{
		if (!values.isEmpty())
		{
			onKo(String.format("✗ %d more values are expected", values.size()));
		}
	}
	
	public abstract void onOk(String msg);
	
	public abstract void onKo(String msg);
}