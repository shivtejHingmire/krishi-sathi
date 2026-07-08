package com.shivar.master.mapper;

import com.shivar.master.dto.TalukaDTO;
import com.shivar.master.entity.Taluka;

public class TalukaMapper {

    private TalukaMapper() {
    }

    public static TalukaDTO toDTO(Taluka taluka) {

        if (taluka == null) {
            return null;
        }

        return TalukaDTO.builder()
                .id(taluka.getId())
                .lgdCode(taluka.getLgdCode())
                .nameEnglish(taluka.getNameEnglish())
                .nameLocal(taluka.getNameLocal())
                .build();
    }
}