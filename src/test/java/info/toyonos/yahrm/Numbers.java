package info.toyonos.yahrm;

import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import info.toyonos.yahrm.command.binding.CommandContextTextDeserializer;
import info.toyonos.yahrm.io.ArrayInputProvider;
import info.toyonos.yahrm.io.AssertOutputStreamOutputReceiver;
import info.toyonos.yahrm.plugin.number.command.NumberCommandFactory;
import info.toyonos.yahrm.Program;

public class Numbers
{
	@Test
	public void case1()
	{
		try
		{
			/*
			 * Objective:
			 * For each two things in the inbox, first subtract the 1st from the 2nd and put the result in the outbox.
			 * And then, subtract the 2nd from the 1st and put the result in the outbox.
			 * Repeat.
			 */
			
			Program case1 = new Program();
			case1.registerCommandFactory(NumberCommandFactory.class);
			
			case1.registerInputProvider(ArrayInputProvider.class, Collections.singletonList((Object) new Object[]{1, 5, 6, 1, -4, -4, 9, -8}));
			case1.registerOutputReceiver(AssertOutputStreamOutputReceiver.class, Collections.singletonList((Object) new Object[]{4, -4, -5, 5, 0, 0, -17, 17}));

			case1.run(CommandContextTextDeserializer.class,
				StringUtils.join(Arrays.asList(
					"Input",
					"CopyTo 0",
					"Input",
					"CopyTo 1",
					"Sub 0",
					"Output",
					"CopyFrom 0",
					"Sub 1",
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
			 * Send all things that are not zero to the outbox.
			 */
			
			Program case1 = new Program();
			case1.registerCommandFactory(NumberCommandFactory.class);
			
			case1.registerInputProvider(ArrayInputProvider.class, Collections.singletonList((Object) new Object[]{4, 0, 3, 8, 0, 0, 4, 22}));
			case1.registerOutputReceiver(AssertOutputStreamOutputReceiver.class, Collections.singletonList((Object) new Object[]{4, 3, 8, 4, 22}));

			case1.run(CommandContextTextDeserializer.class,
				StringUtils.join(Arrays.asList(
					"Input",
					"JumpIfZero 3",
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
	public void case3()
	{
		try
		{
			/*
			 * Objective:
			 * For each two things in the inbox, first add 1 to the 1st and -2 to the 2nd
			 * And then, add the 1st to the 2nd and put the result in the outbox.
			 * Repeat.
			 */
			
			Program case1 = new Program();
			case1.registerCommandFactory(NumberCommandFactory.class);
			
			case1.registerInputProvider(ArrayInputProvider.class, Collections.singletonList((Object) new Object[]{7, 2, 5, 3, 9, -2, 7, -6, 0, -1, -5, 8, -10, -10, -9, 10, 5, 5, -15, -20, 25, -23}));
			case1.registerOutputReceiver(AssertOutputStreamOutputReceiver.class, Collections.singletonList((Object) new Object[]{8, 7, 6, 0, 2, 0, 9, 1}));

			case1.run(CommandContextTextDeserializer.class,
				StringUtils.join(Arrays.asList(
					"Input",
					"CopyTo 0",
					"BumpPlus 0",
					"Input",
					"CopyTo 1",
					"BumpMinus 1",
					"BumpMinus 1",
					"Add 0",
					"JumpIfNegative 0",
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