package info.toyonos.yahrm.io;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ArrayInputProvider implements InputProvider
{
	private Queue<Object> values;

	public ArrayInputProvider(Object... values)
	{
		this.values = new LinkedList<>(Arrays.asList(values));
	}

	@Override
	public Object provide()
	{
		return values.poll();
	}
}
