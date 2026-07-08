package com.shivar.master.mapper;

import com.shivar.master.dto.DistrictDTO;
import com.shivar.master.entity.District;

public class DistrictMapper {

    private DistrictMapper() {
    }

    public static DistrictDTO toDTO(District district) {

        if (district == null) {
            return null;
        }

        return DistrictDTO.builder()
                .id(district.getId())
                .lgdCode(district.getLgdCode())
                .nameEnglish(district.getNameEnglish())
                .nameLocal(district.getNameLocal())
                .build();
    }
}