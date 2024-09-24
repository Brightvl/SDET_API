package com.brightvl.sdet_api.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class Response {

    private int id;
    private List<Integer> important_numbers;
    private String title;
    private boolean verified;
    private Addition addition;


    public static Response createDefaultResponse() {
        Addition addition = Addition.builder()
                .additional_info("Дополнительные сведения")
                .additional_number(123)
                .id(1)
                .build();

        return Response.builder()
                .id(1)
                .important_numbers(List.of(42, 87, 15))
                .title("Заголовок сущности")
                .verified(true)
                .addition(addition)
                .build();
    }
}
