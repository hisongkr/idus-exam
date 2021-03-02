package com.idus.exam.db.base;

import org.mybatis.spring.SqlSessionTemplate;

public interface SqlSessionTemplateDatabaseFactory {
    SqlSessionTemplate getSqlSessionTemplate(String id);
}
