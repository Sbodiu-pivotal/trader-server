package io.pivotal.apac;

import lombok.Data;
import org.joda.money.Money;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TraderServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraderServerApplication.class, args);
	}

}

@Data
class Stock {
    private String symbol;
    private Money open;
    private Money last;
    private Double change;
    private Money high;
    private Money low;

    public Stock(String symbol, Money open) {
        this.symbol = symbol;
        this.open = open;
        last = open;
        high = open;
        low = open;
        change = 0.0d;

    }
}
