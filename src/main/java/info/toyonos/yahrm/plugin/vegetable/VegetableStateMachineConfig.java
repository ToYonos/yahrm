package info.toyonos.yahrm.plugin.vegetable;

import info.toyonos.yahrm.plugin.vegetable.VegetableStateMachineConfig.VegetableEvent;
import info.toyonos.yahrm.plugin.vegetable.VegetableStateMachineConfig.VegetableState;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
@Scope("prototype")
public class VegetableStateMachineConfig extends EnumStateMachineConfigurerAdapter<VegetableState, VegetableEvent>
{
	public enum VegetableState
	{
		SEED, YOUNG_SHOOT, MATURE, ROTTEN
	}

	public enum VegetableEvent
	{
		WATERING
	}

	@Override
	public void configure(StateMachineConfigurationConfigurer<VegetableState, VegetableEvent> config) throws Exception
	{
		config.withConfiguration().autoStartup(true);
	}

	@Override
	public void configure(StateMachineStateConfigurer<VegetableState, VegetableEvent> states) throws Exception
	{
		states.withStates().initial(VegetableState.SEED).end(VegetableState.ROTTEN).states(EnumSet.allOf(VegetableState.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<VegetableState, VegetableEvent> transitions) throws Exception
	{
		transitions
			.withExternal()
			.source(VegetableState.SEED)
			.target(VegetableState.YOUNG_SHOOT)
			.event(VegetableEvent.WATERING)
			.and()
			.withExternal()
			.source(VegetableState.YOUNG_SHOOT)
			.target(VegetableState.MATURE)
			.event(VegetableEvent.WATERING)
			.and()
			.withExternal()
			.source(VegetableState.MATURE)
			.target(VegetableState.ROTTEN)
			.event(VegetableEvent.WATERING);
	}

}