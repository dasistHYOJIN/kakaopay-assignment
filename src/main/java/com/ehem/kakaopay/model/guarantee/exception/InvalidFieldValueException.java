package com.ehem.kakaopay.model.guarantee.exception;

public class InvalidFieldValueException extends IllegalArgumentException {
    public InvalidFieldValueException(final String s) {
        super("데이터의 포맷이 올바르지 않습니다. " + s);
    }
}
