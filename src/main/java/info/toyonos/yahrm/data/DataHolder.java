package info.toyonos.yahrm.data;

import info.toyonos.yahrm.common.ConversionUtils;
import info.toyonos.yahrm.common.IllegalConversionException;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.collections4.list.FixedSizeList;

public abstract class DataHolder
{
	static
	{
		BeanUtilsBean.getInstance().getConvertUtils().register(true, false, 0);
	}

	private List<Object> data;
	
	public DataHolder(int size)
	{
		data = FixedSizeList.fixedSizeList(Arrays.asList(new Object[size]));
	}

	public <T> T read(int from, Class<T> clazz) throws IllegalConversionException
	{
		return ConversionUtils.convert(data.get(from), clazz);
	}

	public Object read(int from)
	{
		return data.get(from);
	}
	
	public Integer readInt(int from) throws IllegalConversionException
	{
		return read(from, Integer.class);
	}

	public void write(int to, Object what)
	{
		data.set(to, what);
	}
}