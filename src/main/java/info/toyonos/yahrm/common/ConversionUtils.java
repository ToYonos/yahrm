package info.toyonos.yahrm.common;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;

public final class ConversionUtils
{
	static
	{
		BeanUtilsBean.getInstance().getConvertUtils().register(true, false, 0);
	}
	
	// TODO custom converter

	public static <T> T convert(Object value, Class<T> clazz) throws IllegalConversionException
	{
		try
		{
			return clazz.cast(ConvertUtils.convert(value, clazz));
		}
		catch (ConversionException e)
		{
			throw new IllegalConversionException(e);
		}
	}
}
