package com.github.patsalyukas.archivemanager.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Boxes")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Box {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

}
