package info.toyonos.yahrm;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import info.toyonos.yahrm.command.binding.CommandContextTextDeserializer;
import info.toyonos.yahrm.io.ArrayInputProvider;
import info.toyonos.yahrm.io.AssertOutputStreamOutputReceiver;
import info.toyonos.yahrm.plugin.vegetable.VegetableDef;
import info.toyonos.yahrm.plugin.vegetable.VegetableStateMachineConfig.VegetableState;
import info.toyonos.yahrm.plugin.vegetable.command.VegetableCommandFactory;
import info.toyonos.yahrm.plugin.vegetable.model.Carrot;
import info.toyonos.yahrm.plugin.vegetable.model.Onion;
import info.toyonos.yahrm.plugin.vegetable.model.Potato;
import info.toyonos.yahrm.Program;

public class Vegetables
{
	@Test
	public void case1()
	{
		try
		{
			/*
			 * Objective:
			 * For each vegetable in the inbox, water it first and put the result in the outbox.
			 * Repeat.
			 */
			
			Program case1 = new Program();
			case1.registerCommandFactory(VegetableCommandFactory.class);

			case1.registerInputProvider(ArrayInputProvider.class,Arrays.asList((Object) new Object[]
			{
				VegetableDef.with(Carrot.class, VegetableState.SEED),
				VegetableDef.with(Potato.class, VegetableState.SEED),
				VegetableDef.with(Onion.class, VegetableState.SEED)
			}));

			case1.registerOutputReceiver(AssertOutputStreamOutputReceiver.class, Arrays.asList((Object) new Object[]
			{
				VegetableDef.with(Carrot.class, VegetableState.YOUNG_SHOOT),
				VegetableDef.with(Potato.class, VegetableState.YOUNG_SHOOT),
				VegetableDef.with(Onion.class, VegetableState.YOUNG_SHOOT)
			}));

			case1.run(CommandContextTextDeserializer.class,
				StringUtils.join(Arrays.asList(
					"Input",
					"CopyTo 0",
					"Water 0",
					"Output",
					"Jump 0"
				), '\n')
			);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void case2()
	{
		try
		{
			/*
			 * Objective:
			 * For each vegetable in the inbox, water it first and put the result in the outbox.
			 * Repeat.
			 */
			
			Program case2 = new Program();
			case2.registerCommandFactory(VegetableCommandFactory.class);

			case2.registerInputProvider(ArrayInputProvider.class,Arrays.asList((Object) new Object[]
			{
				VegetableDef.with(Carrot.class, VegetableState.SEED),
				VegetableDef.with(Potato.class, VegetableState.SEED),
				VegetableDef.with(Onion.class, VegetableState.SEED)
			}));

			case2.registerOutputReceiver(AssertOutputStreamOutputReceiver.class, Arrays.asList((Object) new Object[]
			{
				VegetableDef.with(Potato.class, VegetableState.SEED),
				VegetableDef.with(Onion.class, VegetableState.SEED)
			}));

			case2.run(CommandContextTextDeserializer.class,
				StringUtils.join(Arrays.asList(
					"Input",
					"JumpIfVegetable 0 info.toyonos.yahrm.plugin.vegetable.model.Carrot",
					"Output",
					"Jump 0"
				), '\n')
			);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
}