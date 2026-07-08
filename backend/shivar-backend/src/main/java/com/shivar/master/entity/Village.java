package com.shivar.master.entity;

import com.shivar.entity.BaseMasterEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "villages")
public class Village extends BaseMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lgd_code", nullable = false, unique = true)
    private Long lgdCode;

    @Column(name = "name_english", nullable = false, length = 100)
    private String nameEnglish;

    @Column(name = "name_local", length = 100)
    private String nameLocal;

    @Column(name = "census_code")
    private Long censusCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taluka_id", nullable = false)
    private Taluka taluka;
}