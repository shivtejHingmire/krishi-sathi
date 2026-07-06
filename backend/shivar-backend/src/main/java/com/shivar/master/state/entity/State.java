package com.shivar.master.state.entity;

import com.shivar.entity.BaseMasterEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "states",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_state_code", columnNames = "code"),
                @UniqueConstraint(name = "uk_state_name", columnNames = "name")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class State extends BaseMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, length = 5)
    private String code;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

}