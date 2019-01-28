package ru.gavrilov.model;

public enum TypeTask {
    PRINT("print"),
    COPY("copy"),
    SCAN("scan"),
    FAX("fax");

    TypeTask(String scan) {
        this.nameType = scan;
    }

    private String nameType;

    public String getNameType() {
        return nameType;
    }
}
