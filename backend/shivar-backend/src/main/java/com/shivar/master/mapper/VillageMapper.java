package com.shivar.master.mapper;

import com.shivar.master.dto.VillageDTO;
import com.shivar.master.entity.Village;

public class VillageMapper {

    private VillageMapper() {
    }

    public static VillageDTO toDTO(Village village) {

        if (village == null) {
            return null;
        }

        return VillageDTO.builder()
                .id(village.getId())
                .lgdCode(village.getLgdCode())
                .nameEnglish(village.getNameEnglish())
                .nameLocal(village.getNameLocal())
                .censusCode(village.getCensusCode())
                .build();
    }
}