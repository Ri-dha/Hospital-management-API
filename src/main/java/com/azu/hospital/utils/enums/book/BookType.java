package com.azu.hospital.utils.enums.book;

public enum BookType {
    Import,
    Export;

    private String name;

    BookType() {
    }

    BookType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
