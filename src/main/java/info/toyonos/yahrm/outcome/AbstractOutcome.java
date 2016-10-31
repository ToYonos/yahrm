package info.toyonos.yahrm.outcome;

import info.toyonos.yahrm.data.Buffer;
import info.toyonos.yahrm.data.DataSpace;

public abstract class AbstractOutcome implements Outcome
{
	protected DataSpace dataSpace;
	protected Buffer buffer;

	AbstractOutcome(DataSpace dataSpace, Buffer buffer)
	{
		this.dataSpace = dataSpace;
		this.buffer = buffer;
	}
}
