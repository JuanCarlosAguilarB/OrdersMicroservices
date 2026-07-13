package com.example.demo.sales.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.demo.shared.domain.FactoryRandomValues.*;

public class OrderObjectMother {

    public static Order random() {

        ThreadLocalRandom random = ThreadLocalRandom.current();

        BigDecimal subtotal = BigDecimal.valueOf(random.nextLong(10_000, 5_000_000));

        BigDecimal discount = subtotal
                .multiply(BigDecimal.valueOf(random.nextDouble(0.0, 0.3)));

        BigDecimal tax = subtotal
                .multiply(BigDecimal.valueOf(random.nextDouble(0.0, 0.19)));

        BigDecimal shipping = BigDecimal.valueOf(random.nextLong(5_000, 50_000));

        BigDecimal total = subtotal
                .subtract(discount)
                .add(tax)
                .add(shipping);

        OffsetDateTime created = randomOffsetDateTime();
        OffsetDateTime updated = created.plusMinutes(random.nextLong(1, 1000));

        OrderStatus orderStatus = OrderStatus.PENDING;
        PaymentStatus paymentStatus = PaymentStatus.PENDING;

        return new Order(
                generateRandomLongValue(100_000), // id
                generateRandomString(20),
                generateRandomLongValue(100_000),

                orderStatus,
                paymentStatus,
                getRandomEnumValue(Currency.class),

                subtotal,
                discount,
                tax,
                shipping,
                total,

                generateRandomString(20),
                UUID.randomUUID(),
                UUID.randomUUID(),

                generateRandomString(50),
                generateRandomString(20),
                generateRandomAddress(),

                created,
                created,
                updated,

                true,
                generateRandomIntValue(0, 5)
        );
    }
}
