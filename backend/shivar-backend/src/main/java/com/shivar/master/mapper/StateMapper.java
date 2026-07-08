package com.shivar.master.mapper;

import com.shivar.master.dto.StateDTO;
import com.shivar.master.entity.State;

public class StateMapper {

    private StateMapper() {
        // Prevent instantiation
    }

    /**
     * Convert State Entity to StateDTO
     */
    public static StateDTO toDTO(State state) {

        if (state == null) {
            return null;
        }

        return StateDTO.builder()
                .id(state.getId())
                .lgdCode(state.getLgdCode())
                .nameEnglish(state.getNameEnglish())
                .nameLocal(state.getNameLocal())
                .build();
    }

    /**
     * Convert StateDTO to State Entity
     */
    public static State toEntity(StateDTO dto) {

        if (dto == null) {
            return null;
        }

        State state = new State();

        state.setId(dto.getId());
        state.setLgdCode(dto.getLgdCode());
        state.setNameEnglish(dto.getNameEnglish());
        state.setNameLocal(dto.getNameLocal());

        return state;
    }
}