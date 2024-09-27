package com.brightvl.sdet_api.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Setter
@Getter
@Builder
public class Response {

    private List<Integer> important_numbers;
    private String title;
    private boolean verified;
    private Addition addition;


    public static Response createDefaultResponse() {
        Addition addition = Addition.builder().build();

        return Response.builder()
                .important_numbers(generateRandomNumbers())
                .title("Заголовок сущности")
                .verified(true)
                .addition(addition)
                .build();
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
        return "{" +
                "important_numbers: " + important_numbers +
                ", title:" + title +
                ", verified:" + verified +
                ", addition:" + addition +
                '}';
    }
}
