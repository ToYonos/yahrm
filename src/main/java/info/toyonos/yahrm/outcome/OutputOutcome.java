package info.toyonos.yahrm.outcome;

import info.toyonos.yahrm.data.Buffer;
import info.toyonos.yahrm.data.DataSpace;
import info.toyonos.yahrm.io.OutputReceiver;

public class OutputOutcome extends AbstractOutcome
{
	private OutputReceiver receiver;
	
	OutputOutcome(DataSpace dataSpace, Buffer buffer, OutputReceiver receiver)
	{
		super(dataSpace, buffer);
		this.receiver = receiver;
	}

	public void face()
	{
		Object data = buffer.read();
		if (data != null)
		{
			receiver.receive(data);
			buffer.write(null);
		}
		else
		{
			throw new IllegalStateException("Unable to face this outcome : the buffer is empty"); 
		}
	}
}
