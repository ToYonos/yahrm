package info.toyonos.yahrm.common;

import org.apache.commons.lang3.tuple.Pair;

public final class ParameterUtils
{
	public static <A> A extractParameter(Class<A> clazz, Object... parameters) throws IllegalConversionException
	{
		return ConversionUtils.convert(parameters[0], clazz);
	}
	
	public static <A, B> Pair<A, B> extractParameter(Class<A> clazz1, Class<B> clazz2, Object... parameters) throws IllegalConversionException
	{
		return Pair.of(
			ConversionUtils.convert(parameters[0], clazz1),
			ConversionUtils.convert(parameters[1], clazz2)
		);
	}
}
