package info.toyonos.yahrm.io;

import java.io.OutputStream;
import java.io.PrintStream;

import info.toyonos.yahrm.common.FatalErrorException;

public class AssertOutputStreamOutputReceiver extends AssertOutputReceiver
{
	protected PrintStream output;

	public AssertOutputStreamOutputReceiver(Object... values)
	{
		super(values);
		this.output = System.out;
	}

	public AssertOutputStreamOutputReceiver(OutputStream output, Object... values)
	{
		super(values);
		this.output = new PrintStream(output);
	}
	
	public void onOk(String msg)
	{
		output.println(msg);
	}
	
	public void onKo(String msg)
	{
		throw new FatalErrorException(msg);
	}
}