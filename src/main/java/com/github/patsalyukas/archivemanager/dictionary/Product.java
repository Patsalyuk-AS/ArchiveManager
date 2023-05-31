package com.github.patsalyukas.archivemanager.dictionary;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Product {

    BOX(1, "box"),
    DOCUMENT(2, "document");

    private int code;
    private String name;
}
