package com.shivar.master.entity;

import com.shivar.entity.BaseMasterEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "districts")
public class District extends BaseMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lgd_code", nullable = false, unique = true)
    private Long lgdCode;

    @Column(name = "name_english", nullable = false, length = 100)
    private String nameEnglish;

    @Column(name = "name_local", length = 100)
    private String nameLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    private List<Taluka> talukas = new ArrayList<>();
}