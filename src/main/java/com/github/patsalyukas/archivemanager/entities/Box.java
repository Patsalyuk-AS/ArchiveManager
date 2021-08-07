package com.github.patsalyukas.archivemanager.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Boxes")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(exclude = {"id"})
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @ToString.Exclude
    private long id;

    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NonNull
    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

}
