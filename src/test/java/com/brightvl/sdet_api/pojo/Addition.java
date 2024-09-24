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
    private int id;
}


