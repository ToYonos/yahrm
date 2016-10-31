package info.toyonos.yahrm.configuration;

import info.toyonos.yahrm.data.Buffer;
import info.toyonos.yahrm.data.DataSpace;
import info.toyonos.yahrm.data.SimpleBuffer;
import info.toyonos.yahrm.data.SimpleDataSpace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("info.toyonos.yahrm")
public class ContextConfiguration
{
	@Bean
	public Buffer getBuffer()
	{
		return new SimpleBuffer();
	}

	@Bean
	public DataSpace getDataSpace()
	{
		return new SimpleDataSpace(10);
	}
}
