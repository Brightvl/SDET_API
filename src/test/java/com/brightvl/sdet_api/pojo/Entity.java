package com.brightvl.sdet_api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Setter
@Getter
@Builder
public class Entity {
    private static final Logger logger = LoggerFactory.getLogger(Entity.class);

    @JsonProperty("important_numbers")
    private List<Integer> importantNumbers;

    private String title;
    private boolean verified;
    private Addition addition;

    @Description("Создание сущности")
    public static Entity createDefaultEntity() {
        Addition addition = Addition.builder().build();

        return Entity.builder()
                .importantNumbers(generateRandomNumbers())
                .title(generateRandomTitle())
                .verified(generateRandomBoolean())
                .addition(addition)
                .build();
    }

    /**
     * Генерирует случайное значение для поля verified
     *
     * @return случайное boolean значение
     */
    private static boolean generateRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Генерирует случайный заголовок для сущности
     *
     * @return случайный заголовок
     */
    private static String generateRandomTitle() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        return "Заголовок сущности " + randomNumber;
    }

    /**
     * Формирует список рандомных чисел
     *
     * @return список
     */
    private static List<Integer> generateRandomNumbers() {
        Random random = new Random();
        int count = random.nextInt(8) + 3;
        return IntStream.range(0, count)
                .mapToObj(i -> random.nextInt(101))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.error("Ошибка сериализации объекта Entity в JSON", e);
            return super.toString();
        }
    }

}
