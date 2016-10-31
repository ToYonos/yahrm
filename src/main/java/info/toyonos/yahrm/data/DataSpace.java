package info.toyonos.yahrm.data;

import info.toyonos.yahrm.common.IllegalConversionException;

public interface DataSpace
{
	Object read(int from);
	
	Integer readInt(int from) throws IllegalConversionException;
	
	<T> T read(int from, Class<T> clazz) throws IllegalConversionException;
	
	void write(int to, Object what);
}