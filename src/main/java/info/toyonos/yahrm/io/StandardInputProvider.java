package info.toyonos.yahrm.io;

import java.util.Scanner;

public class StandardInputProvider implements InputProvider
{
	private Scanner scanner;

	public StandardInputProvider()
	{
		scanner = new Scanner(System.in);
	}
	
	@Override
	public Object provide()
	{
		return scanner.nextLine();
	}
}
