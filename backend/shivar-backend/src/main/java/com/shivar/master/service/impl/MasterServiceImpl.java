package com.shivar.master.service.impl;

import com.shivar.master.dto.DistrictDTO;
import com.shivar.master.dto.StateDTO;
import com.shivar.master.dto.TalukaDTO;
import com.shivar.master.dto.VillageDTO;
import com.shivar.master.mapper.DistrictMapper;
import com.shivar.master.mapper.StateMapper;
import com.shivar.master.mapper.TalukaMapper;
import com.shivar.master.mapper.VillageMapper;
import com.shivar.master.repository.DistrictRepository;
import com.shivar.master.repository.StateRepository;
import com.shivar.master.repository.TalukaRepository;
import com.shivar.master.repository.VillageRepository;
import com.shivar.master.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {

    private final StateRepository stateRepository;
    private final DistrictRepository districtRepository;
    private final TalukaRepository talukaRepository;
    private final VillageRepository villageRepository;

    @Override
    public List<StateDTO> getAllStates() {

        return stateRepository.findAll()
                .stream()
                .map(StateMapper::toDTO)
                .toList();
    }

    @Override
    public List<DistrictDTO> getDistrictsByState(Long stateId) {

        return districtRepository.findByStateIdOrderByNameEnglishAsc(stateId)
                .stream()
                .map(DistrictMapper::toDTO)
                .toList();
    }

    @Override
    public List<TalukaDTO> getTalukasByDistrict(Long districtId) {

        return talukaRepository.findByDistrictIdOrderByNameEnglishAsc(districtId)
                .stream()
                .map(TalukaMapper::toDTO)
                .toList();
    }

    @Override
    public List<VillageDTO> getVillagesByTaluka(Long talukaId) {

        return villageRepository.findByTalukaIdOrderByNameEnglishAsc(talukaId)
                .stream()
                .map(VillageMapper::toDTO)
                .toList();
    }
}