package info.toyonos.yahrm.plugin.vegetable.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.plugin.vegetable.VegetableStateMachineConfig.VegetableState;

@Component
@Scope("prototype")
public final class Potato extends Vegetable
{
	public Potato()
	{
		super();
	}

	public Potato(VegetableState state)
	{
		super(state);
	}
}

