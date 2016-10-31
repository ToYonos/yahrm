package info.toyonos.yahrm.data;

import info.toyonos.yahrm.common.IllegalConversionException;


public class SimpleBuffer extends DataHolder implements Buffer
{
	public SimpleBuffer()
	{
		super(1);
	}

	@Override
	public Object read()
	{
		return read(0);
	}
	
	@Override
	public Integer readInt() throws IllegalConversionException
	{
		return readInt(0);
	}
	
	@Override
	public <T> T read(Class<T> clazz) throws IllegalConversionException
	{
		return read(0, clazz);
	}

	@Override
	public void write(Object what)
	{
		write(0, what);
	}
}