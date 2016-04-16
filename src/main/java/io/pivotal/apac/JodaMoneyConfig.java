package io.pivotal.apac;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Configuration
public class JodaMoneyConfig {

    private RoundingMode roundingMode = RoundingMode.HALF_UP;

    @Bean
    public Module jodaMoneyJackson() {
        return new SimpleModule()
                .addDeserializer(Money.class, new MoneyDeserializer(this.roundingMode))
                .addSerializer(Money.class, new MoneySerializer());
    }

    private static class MoneySerializer extends JsonSerializer<Money> {

        @Override
        public void serialize(Money money, JsonGenerator json, SerializerProvider serializerProvider)
                throws IOException {
            json.writeStartObject();
            json.writeStringField("amount", money.getAmount().toPlainString());
            json.writeStringField("currency", money.getCurrencyUnit().getCode());
            json.writeEndObject();
        }
    }

    private static class MoneyDeserializer extends JsonDeserializer<Money> {

        private RoundingMode roundingMode;

        MoneyDeserializer(RoundingMode roundingMode) {
            this.roundingMode = roundingMode;
        }

        @Override
        public Money deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            ObjectCodec objectCodec = jsonParser.getCodec();
            JsonNode node = objectCodec.readTree(jsonParser);

            String currency = node.get("currency").asText();
            String amount = node.get("amount").asText();

            return Money.of(
                    CurrencyUnit.of(currency),
                    new BigDecimal(amount),
                    this.roundingMode
            );
        }
    }
}