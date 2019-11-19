package com.sharma.nks;

import com.sharma.nks.config.CucumberBaseStep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest3WithSpringbootApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Bean
	public CucumberBaseStep getCucumberBaseStep(){
		return new CucumberBaseStep();
	}
}
