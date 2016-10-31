package info.toyonos.yahrm.plugin.vegetable;

import org.apache.commons.lang3.tuple.Pair;

import info.toyonos.yahrm.common.BeanDef;
import info.toyonos.yahrm.plugin.vegetable.VegetableStateMachineConfig.VegetableState;
import info.toyonos.yahrm.plugin.vegetable.model.Vegetable;

public class VegetableDef<T extends Vegetable> extends BeanDef<T, VegetableState>
{	
	private VegetableDef(Pair<Class<T>, VegetableState> pair)
	{
		super(pair);
	}

	public static <T extends Vegetable> VegetableDef<T> with(Class<T> vegetableClass, VegetableState state)
	{
		return new VegetableDef<T>(Pair.of(vegetableClass, state));
	}
	
	@Override
	public String toString()
	{
		return getType().getSimpleName() + " (" +  getArgs() + ")";
	}
}