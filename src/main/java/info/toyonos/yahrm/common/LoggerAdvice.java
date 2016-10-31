package info.toyonos.yahrm.common;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.MethodBeforeAdvice;

// TODO proper logger
public class LoggerAdvice implements MethodBeforeAdvice
{
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable
	{
		if (method.getName().equals("execute"))
		{
			StringBuilder sb = new StringBuilder("Executing " + target.getClass().getSimpleName());
			if (args.length > 0 && ((Object[]) args[0]).length > 0) sb.append(" with parameters " + Arrays.toString((Object[]) args[0]));
			System.out.println(sb.toString());
		}
	}
}