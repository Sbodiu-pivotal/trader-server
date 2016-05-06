package io.pivotal.apac;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.money.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@EnableJpaAuditing
@EnableJpaRepositories
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class TraderServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraderServerApplication.class, args);
	}

    @Bean
    CommandLineRunner runner(StockRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                repository.save(new Stock("EMC", Money.parse("USD 30.00")));
                repository.findAll().stream().forEach(System.out::println);
            }
        };
    }

}

interface StockRepository extends JpaRepository<Stock, String> {

}

@Data
@Entity
class Stock {
    @javax.persistence.Id
    private String symbol;

    @Type(type = "io.pivotal.apac.SingleColumnMoneyUserType")
    private Money open;
    @Type(type = "io.pivotal.apac.SingleColumnMoneyUserType")
    private Money last;

    private Double change;
    @Type(type = "io.pivotal.apac.SingleColumnMoneyUserType")
    private Money high;
    @Type(type = "io.pivotal.apac.SingleColumnMoneyUserType")
    private Money low;

    public Stock() {

    }

    public Stock(String symbol, Money open) {
        this.symbol = symbol;
        this.open = open;
        last = open;
        high = open;
        low = open;
        change = 0.0d;

    }
}
