package info.toyonos.yahrm.plugin.vegetable.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.plugin.vegetable.VegetableStateMachineConfig.VegetableState;

@Component
@Scope("prototype")
public final class Carrot extends Vegetable
{
	public Carrot()
	{
		super();
	}

	public Carrot(VegetableState state)
	{
		super(state);
	}
}
