package com.shivar.master.service;

import com.shivar.master.dto.DistrictDTO;
import com.shivar.master.dto.StateDTO;
import com.shivar.master.dto.TalukaDTO;
import com.shivar.master.dto.VillageDTO;

import java.util.List;

public interface MasterService {

    /**
     * Get all states.
     *
     * @return list of states
     */
    List<StateDTO> getAllStates();

    /**
     * Get districts by state.
     *
     * @param stateId state id
     * @return list of districts
     */
    List<DistrictDTO> getDistrictsByState(Long stateId);

    /**
     * Get talukas by district.
     *
     * @param districtId district id
     * @return list of talukas
     */
    List<TalukaDTO> getTalukasByDistrict(Long districtId);

    /**
     * Get villages by taluka.
     *
     * @param talukaId taluka id
     * @return list of villages
     */
    List<VillageDTO> getVillagesByTaluka(Long talukaId);

}