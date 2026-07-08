package com.shivar.master.repository;

import com.shivar.master.entity.Taluka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TalukaRepository extends JpaRepository<Taluka, Long> {

    Optional<Taluka> findByLgdCode(Long lgdCode);

    List<Taluka> findByDistrictIdOrderByNameEnglishAsc(Long districtId);

    List<Taluka> findAll();

}