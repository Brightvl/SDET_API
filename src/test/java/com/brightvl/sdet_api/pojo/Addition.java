package com.brightvl.sdet_api.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Addition {

    /**
     * Дополнительная информация
     */
    @Builder.Default
    private String additional_info = "Дополнительные сведения";

    /**
     * Дополнительный номер
     */
    @Builder.Default
    private int additional_number = 123;

    @Override
    public String toString() {
        return "{" +
                "additional_info:" + additional_info +
                ", additional_number:" + additional_number +
                '}';
    }
}


