package com.example.demo.sales.api;

import com.example.demo.sales.application.OrderCreateDto;
import com.example.demo.sales.domain.Currency;

import java.net.InetAddress;

public class CreateOrderRequestMapper {

    public static OrderCreateDto toDto(CreateOrderRequest request, InetAddress ipAddress, String Source) {
        if (request == null) {
            return null;
        }

        return OrderCreateDto.builder()
                .customerId(request.getCustomerId())
                .currency(Currency.valueOf(request.getCurrency()))

                .subtotalAmount(request.getSubtotalAmount())
                .discountAmount(request.getDiscountAmount())
                .taxAmount(request.getTaxAmount())
                .shippingAmount(request.getShippingAmount())

                .paymentMethod(request.getPaymentMethod())
                .shippingAddressId(request.getShippingAddressId())
                .billingAddressId(request.getBillingAddressId())

                .notes(request.getNotes())

                // metadata
                .source(Source)
                .ipAddress(ipAddress)

                .build();
    }

}


