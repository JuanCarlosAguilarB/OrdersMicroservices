package com.example.demo.sales.domain;

import com.example.demo.sales.application.OrderCreateDto;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.demo.shared.domain.FactoryRandomValues.*;
import static com.example.demo.shared.domain.FactoryRandomValues.generateRandomAddress;
import static com.example.demo.shared.domain.FactoryRandomValues.generateRandomString;
import static com.example.demo.shared.domain.FactoryRandomValues.getRandomEnumValue;

public class OrderCreateDtoMother {

    public static OrderCreateDto random() {

        ThreadLocalRandom random = ThreadLocalRandom.current();

        BigDecimal subtotal = BigDecimal.valueOf(random.nextLong(10_000, 5_000_000));

        // discount: 0% – 30% of subtotal
        BigDecimal discount = subtotal
                .multiply(BigDecimal.valueOf(random.nextDouble(0.0, 0.3)));

        // tax: 0% – 19% (realistic VAT style)
        BigDecimal tax = subtotal
                .multiply(BigDecimal.valueOf(random.nextDouble(0.0, 0.19)));

        // shipping: reasonable range
        BigDecimal shipping = BigDecimal.valueOf(random.nextLong(5_000, 50_000));

        return OrderCreateDto.builder()
                .customerId(generateRandomLongValue(100_000))
                .currency(getRandomEnumValue(Currency.class))
                .subtotalAmount(subtotal)
                .discountAmount(discount)
                .taxAmount(tax)
                .shippingAmount(shipping)
                .paymentMethod(generateRandomString(20))
                .shippingAddressId(UUID.randomUUID())
                .billingAddressId(UUID.randomUUID())
                .notes(generateRandomString(50))
                .ipAddress(generateRandomAddress())
                .source(generateRandomString(20))
                .build();
    }
}