package com.brightvl.sdet_api.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter
@Getter
@Builder
public class Addition {
    /**
     * Дополнительная информация
     */
    @Builder.Default
    private String additionalInfo = getRandomAdditionalInfo();
    /**
     * Дополнительный номер
     */
    @Builder.Default
    private int additionalNumber = getRandomAdditionalNumber();

    private static String getRandomAdditionalInfo() {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'A');
        return "Информация " + randomChar;
    }

    /**
     * Формирует рандомное число
     *
     * @return число
     */
    private static int getRandomAdditionalNumber() {
        return new Random().nextInt(1000);
    }
}


