package info.toyonos.yahrm.common;

import info.toyonos.yahrm.data.Buffer;
import info.toyonos.yahrm.data.DataSpace;
import info.toyonos.yahrm.io.InputProvider;
import info.toyonos.yahrm.io.OutputReceiver;
import info.toyonos.yahrm.outcome.OutcomeFactory;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Context
{
	@JsonIgnore
	@Autowired
	private OutcomeFactory outcomeFactory;
	
	@JsonIgnore
	@Autowired
	private Buffer buffer;

	@JsonIgnore
	@Autowired
	private DataSpace dataSpace;

	@JsonIgnore
	@Autowired
	private InputProvider inputProvider;

	@JsonIgnore
	@Autowired
	private OutputReceiver outputReceiver;

	public OutcomeFactory getOutcomeFactory()
	{
		return outcomeFactory;
	}

	public Buffer getBuffer()
	{
		return buffer;
	}

	public DataSpace getDataSpace()
	{
		return dataSpace;
	}

	public InputProvider getInputProvider()
	{
		return inputProvider;
	}

	public OutputReceiver getOutputReceiver()
	{
		return outputReceiver;
	}
}
