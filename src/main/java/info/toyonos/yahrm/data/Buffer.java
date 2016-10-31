package info.toyonos.yahrm.data;

import info.toyonos.yahrm.common.IllegalConversionException;

public interface Buffer
{
	Object read();
	
	Integer readInt() throws IllegalConversionException;
	
	<T> T read(Class<T> clazz) throws IllegalConversionException;
	
	void write(Object what);
}
