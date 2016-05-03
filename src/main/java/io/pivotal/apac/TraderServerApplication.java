package io.pivotal.apac;

import lombok.Data;
import org.joda.money.Money;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@EnableJpaAuditing
@EnableJpaRepositories
@EnableDiscoEurekaClient
@EnableHystrix
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
