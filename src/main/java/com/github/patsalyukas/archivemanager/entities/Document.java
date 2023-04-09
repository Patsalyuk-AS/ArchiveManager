package com.github.patsalyukas.archivemanager.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Documents")
@Getter
@Setter
@ToString(exclude = {"id", "box"})
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id", "box"})
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NonNull
    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOX_ID")
    private Box box;

}

