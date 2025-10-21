package com.avaliador.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusFilme {
    assistido,
    assistindo,
    queroAssistir;

    @JsonCreator
    public static StatusFilme fromValue(String value) {
        for (StatusFilme status : StatusFilme.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + value);
    }
}
