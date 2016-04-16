package io.pivotal.apac;

import lombok.Data;
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
    private String open;
    private String last;
    private String change;
    private String high;
    private String low;

    public Stock(String symbol, String open) {
        this.symbol = symbol;
        this.open = open;
        last = open;
        high = open;
        low = open;
        change = "0.0";

    }
}
