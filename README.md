# yahrm

As I really enjoyed the game [Human Resource Machine](http://tomorrowcorporation.com/humanresourcemachine), I rewrote the game engine with some modifications.

With a plugin system, the engine can handle various kind of objects. For now, it handles numbers (like in the original game) and `Vegetable`, a simple object model for proof-of-concept purpose.

It's a not perfect, still a work in progress, I just did it for fun actually.

## Numbers example

*Objective :*
 * *For each two things in the inbox, first subtract the 1st from the 2nd and put the result in the outbox.*
 * *And then, subtract the 2nd from the 1st and put the result in the outbox.*
 * *Repeat.*

```java
// Creating the program
Program pgm = new Program();

// Registering a command factory
pgm.registerCommandFactory(NumberCommandFactory.class);

// Registering an input provider 
pgm.registerInputProvider(
	ArrayInputProvider.class,
	Collections.singletonList((Object) new Object[]{1, 5, 6, 1, -4, -4, 9, -8})
);

// Registering an output receiver
pgm.registerOutputReceiver(
	AssertOutputStreamOutputReceiver.class,
	Collections.singletonList((Object) new Object[]{4, -4, -5, 5, 0, 0, -17, 17})
);

// Running the program, feeding it commands
pgm.run(CommandContextTextDeserializer.class,
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
```

Output :

```
Executing InputCommand
Executing CopyToCommand with parameters [0]
Executing InputCommand
Executing CopyToCommand with parameters [1]
Executing SubCommand with parameters [0]
Executing OutputCommand
✓ Output : '4', expected : '4'
Executing CopyFromCommand with parameters [0]
Executing SubCommand with parameters [1]
Executing OutputCommand
✓ Output : '-4', expected : '-4'
Executing JumpCommand with parameters [0]
Executing InputCommand
Executing CopyToCommand with parameters [0]
Executing InputCommand
Executing CopyToCommand with parameters [1]
Executing SubCommand with parameters [0]
Executing OutputCommand
✓ Output : '-5', expected : '-5'
Executing CopyFromCommand with parameters [0]
Executing SubCommand with parameters [1]
Executing OutputCommand
✓ Output : '5', expected : '5'
Executing JumpCommand with parameters [0]
Executing InputCommand
Executing CopyToCommand with parameters [0]
Executing InputCommand
Executing CopyToCommand with parameters [1]
Executing SubCommand with parameters [0]
Executing OutputCommand
✓ Output : '0', expected : '0'
Executing CopyFromCommand with parameters [0]
Executing SubCommand with parameters [1]
Executing OutputCommand
✓ Output : '0', expected : '0'
Executing JumpCommand with parameters [0]
Executing InputCommand
Executing CopyToCommand with parameters [0]
Executing InputCommand
Executing CopyToCommand with parameters [1]
Executing SubCommand with parameters [0]
Executing OutputCommand
✓ Output : '-17', expected : '-17'
Executing CopyFromCommand with parameters [0]
Executing SubCommand with parameters [1]
Executing OutputCommand
✓ Output : '17', expected : '17'
Executing JumpCommand with parameters [0]
Executing InputCommand
```

## Vegetable example

*Objective :*
 * *For each vegetable in the inbox, water it first and put the result in the outbox.*
 * *Repeat.*

```java
// Creating the program
Program pgm = new Program();

// Registering a command factory
pgm.registerCommandFactory(VegetableCommandFactory.class);

// Registering an input provider 
pgm.registerInputProvider(ArrayInputProvider.class,Arrays.asList((Object) new Object[]
{
	VegetableDef.with(Carrot.class, VegetableState.SEED),
	VegetableDef.with(Potato.class, VegetableState.SEED),
	VegetableDef.with(Onion.class, VegetableState.SEED)
}));

// Registering an output receiver
pgm.registerOutputReceiver(AssertOutputStreamOutputReceiver.class, Arrays.asList((Object) new Object[]
{
	VegetableDef.with(Carrot.class, VegetableState.YOUNG_SHOOT),
	VegetableDef.with(Potato.class, VegetableState.YOUNG_SHOOT),
	VegetableDef.with(Onion.class, VegetableState.YOUNG_SHOOT)
}));

// Running the program, feeding it commands
pgm.run(CommandContextTextDeserializer.class,
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
```

Output :

```
Executing InputCommand
Executing CopyToCommand with parameters [0]
Executing WaterCommand with parameters [0]
Executing OutputCommand
✓ Output : 'Carrot (YOUNG_SHOOT)', expected : 'Carrot (YOUNG_SHOOT)'
Executing JumpCommand with parameters [0]
Executing InputCommand
Executing CopyToCommand with parameters [0]
Executing WaterCommand with parameters [0]
Executing OutputCommand
✓ Output : 'Potato (YOUNG_SHOOT)', expected : 'Potato (YOUNG_SHOOT)'
Executing JumpCommand with parameters [0]
Executing InputCommand
Executing CopyToCommand with parameters [0]
Executing WaterCommand with parameters [0]
Executing OutputCommand
✓ Output : 'Onion (YOUNG_SHOOT)', expected : 'Onion (YOUNG_SHOOT)'
Executing JumpCommand with parameters [0]
Executing InputCommand
```
