package com.github.patsalyukas.archivemanager.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Documents")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id", "box"})
public class Document {

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

    @OneToOne
    @JoinColumn(name = "BOX")
    private Box box;

}
