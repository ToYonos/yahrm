package info.toyonos.yahrm.plugin.vegetable.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import info.toyonos.yahrm.plugin.vegetable.VegetableStateMachineConfig.VegetableState;

@Component
@Scope("prototype")
public final class Onion extends Vegetable
{
	public Onion()
	{
		super();
	}

	public Onion(VegetableState state)
	{
		super(state);
	}
}
