package com.distributed.tracing.CheckoutService.enums;

public enum OrderStatus {

    PLACED("PLACED"),
    CONFIRMED("CONFIRMED"),
    REJECTED("REJECTED"),

    FAILED("FAILED"),

    CANCELLED("CANCELLED"),

    DELIVERED("DELIVERED");

    String code;

    OrderStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
