package com.example.demo.sales.domain;

import java.math.BigDecimal;
import java.util.UUID;

import static com.example.demo.shared.domain.FactoryRandomValues.generateRandomBigDecimal;
import static com.example.demo.shared.domain.FactoryRandomValues.generateRandomIntValue;
import static com.example.demo.shared.domain.FactoryRandomValues.generateRandomLongValue;
import static com.example.demo.shared.domain.FactoryRandomValues.generateRandomString;
import static com.example.demo.shared.domain.FactoryRandomValues.getRandomEnumValue;
import static com.example.demo.shared.domain.FactoryRandomValues.randomOffsetDateTime;

public  class OrderObjectMother {

    public static Order getRandomOrder (){
        BigDecimal bigDecimalLimit = new  BigDecimal(50000000);
        int longLimit = 100000;
        int stringLimit = 100;

        return new Order(
                generateRandomLongValue(longLimit),
                generateRandomString(stringLimit),
                generateRandomLongValue(longLimit), // customerId

                getRandomEnumValue(OrderStatus.class),
                getRandomEnumValue(PaymentStatus.class),
                getRandomEnumValue(Currency.class),

                generateRandomBigDecimal(bigDecimalLimit),
                generateRandomBigDecimal(bigDecimalLimit),
                generateRandomBigDecimal(bigDecimalLimit),
                generateRandomBigDecimal(bigDecimalLimit),
                generateRandomBigDecimal(bigDecimalLimit),

                generateRandomString(stringLimit),
                UUID.randomUUID(),
                UUID.randomUUID(),

                generateRandomString(stringLimit),
                generateRandomString(stringLimit),
                generateRandomString(stringLimit),

                randomOffsetDateTime(),
                randomOffsetDateTime(),
                randomOffsetDateTime(),

                false,
                generateRandomIntValue(0, 10) // version --> optimistic locking, random small non-negative int

        );
    }

}
