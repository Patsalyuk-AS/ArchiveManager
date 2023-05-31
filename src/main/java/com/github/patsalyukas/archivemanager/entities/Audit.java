package com.github.patsalyukas.archivemanager.entities;

import com.github.patsalyukas.archivemanager.dictionary.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "AUDIT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Audit {

    /**
     * Идентификатор таблицы AUDIT
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    /**
     * Продукт для доступа
     */
    @Column(name = "PRODUCT", nullable = false)
    private Product product;

    /**
     * Код продукта к которому получен доступ
     */
    @Column(name = "PRODUCT_CODE", nullable = false)
    private String productCode;

    /**
     * Дата и время записи аудита
     */
    @CreationTimestamp
    @Column(name = "CREATED_TIME", nullable = false)
    private Instant createdTime;
}
