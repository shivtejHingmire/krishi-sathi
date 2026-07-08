package com.shivar.master.repository;

import com.shivar.master.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    Optional<District> findByLgdCode(Long lgdCode);

    List<District> findByStateIdOrderByNameEnglishAsc(Long stateId);

    List<District> findAll();

}