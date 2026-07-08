package com.shivar.master.repository;

import com.shivar.master.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    Optional<State> findByLgdCode(Long lgdCode);

    boolean existsByLgdCode(Long lgdCode);
    List<State> findAll();
}