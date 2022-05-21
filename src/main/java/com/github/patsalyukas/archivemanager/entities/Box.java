package com.github.patsalyukas.archivemanager.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Boxes")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private long id;

    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NonNull
    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "box")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Document> documents;

    public void addDocument(Document document) {
        documents.add(document);
    }

}
