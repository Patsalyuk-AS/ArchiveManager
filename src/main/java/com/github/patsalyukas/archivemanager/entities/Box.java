package com.github.patsalyukas.archivemanager.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Boxes")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return name.equals(box.name) && code.equals(box.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }
}
