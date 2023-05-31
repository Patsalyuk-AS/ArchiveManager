package com.github.patsalyukas.archivemanager.dto;

import com.github.patsalyukas.archivemanager.dictionary.Product;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class AuditDTO {

    private final Long id;

    private final Product product;

    private final String productCode;

    private final Instant createdTime;
}
