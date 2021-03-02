package com.idus.exam.db.config;


public enum SqlSessionTemplateType {
    EXAM_WRITE("examWriteSqlSessionTemplate"),
    EXAM_READ("examReadSqlSessionTemplate");

    private String id;

    SqlSessionTemplateType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
