package io.pivotal.apac;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraderServerApplicationTests {

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void contextLoads() throws IOException {
		Money openAmount = Money.parse("USD 38.87");
		Stock test = new Stock("GM", openAmount);
		Assert.assertEquals(test.getSymbol(), "GM");

		String json = objectMapper.writeValueAsString(openAmount);
		Money actualOpenAmount = objectMapper.readValue(json, Money.class);

		Assert.assertEquals(openAmount, actualOpenAmount);
	}

}
