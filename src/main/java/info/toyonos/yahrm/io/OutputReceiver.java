package info.toyonos.yahrm.io;

public interface OutputReceiver
{
	void receive(Object what);
	
	void onEnd();
}
