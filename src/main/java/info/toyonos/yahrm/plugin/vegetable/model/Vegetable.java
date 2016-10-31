package info.toyonos.yahrm.plugin.vegetable.model;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import info.toyonos.yahrm.plugin.vegetable.VegetableStateMachineConfig.VegetableEvent;
import info.toyonos.yahrm.plugin.vegetable.VegetableStateMachineConfig.VegetableState;

public abstract class Vegetable
{
	@Autowired
	private StateMachine<VegetableState, VegetableEvent> stateMachine;
	private VegetableState initialState;
	
	
	public Vegetable()
	{
		initialState = null;
	}
	
	public Vegetable(final VegetableState state)
	{
		initialState = state; 
	}
	
	@PostConstruct
	public void initState()
	{
		stateMachine.getStateMachineAccessor().doWithAllRegions(new StateMachineFunction<StateMachineAccess<VegetableState, VegetableEvent>>()
		{
			@Override
			public void apply(StateMachineAccess<VegetableState, VegetableEvent> function)
			{
				function.resetStateMachine(new DefaultStateMachineContext<VegetableState, VegetableEvent>(initialState, null, null, null));
			}
		});
	}
	
	public void water()
	{
		stateMachine.sendEvent(VegetableEvent.WATERING);
	}

	public VegetableState getState()
	{
		return stateMachine.getState().getId();
	}
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + " (" + stateMachine.getState().getId() + ")";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stateMachine == null) ? 0 : stateMachine.getState().getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Vegetable other = (Vegetable) obj;
		if (stateMachine == null)
		{
			if (other.stateMachine != null) return false;
		}
		else if (stateMachine.getState().getId() != other.stateMachine.getState().getId()) return false;
		return true;
	}
}
