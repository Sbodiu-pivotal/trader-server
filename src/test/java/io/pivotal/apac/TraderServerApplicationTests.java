package io.pivotal.apac;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraderServerApplicationTests {

	@Test
	public void contextLoads() {
		Stock test = new Stock("GM", "38.87");
		Assert.assertEquals(test.getSymbol(), "GM");
	}

}
