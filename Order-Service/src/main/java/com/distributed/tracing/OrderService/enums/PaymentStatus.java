package com.distributed.tracing.OrderService.enums;

public enum PaymentStatus {

    INCOMPLETE("INCOMPLETE"),
    PENDING("PENDING"),
    COMPLETED("COMPLETED");

    String code;

    PaymentStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
