package com.shivar.master.repository;

import com.shivar.master.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {

    Optional<Village> findByLgdCode(Long lgdCode);

    List<Village> findByTalukaIdOrderByNameEnglishAsc(Long talukaId);

    List<Village> findAll();

}