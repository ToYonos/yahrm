package info.toyonos.yahrm.common;

import org.apache.commons.lang3.tuple.Pair;

public abstract class BeanDef<A, B>
{
	private Pair<Class<A>, B> pair;
	
	protected BeanDef(Pair<Class<A>, B> pair)
	{
		this.pair = pair;
	}
	
	public final Class<A> getType()
	{
		return pair.getLeft();
	}
	
	public final B getArgs()
	{
		return pair.getRight();
	}
}
