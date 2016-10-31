package info.toyonos.yahrm.io;

public class StandardOutputReceiver implements OutputReceiver
{
	@Override
	public void receive(Object what)
	{
		System.out.println(what);
	}

	@Override
	public void onEnd()
	{	
	}
}