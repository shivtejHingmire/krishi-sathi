package com.shivar.master.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VillageDTO {

    private Long id;

    private Long lgdCode;

    private String nameEnglish;

    private String nameLocal;

    private Long censusCode;
}