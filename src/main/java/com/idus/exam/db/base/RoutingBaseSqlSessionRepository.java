package com.idus.exam.db.base;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class RoutingBaseSqlSessionRepository {
    @Autowired
    public SqlSessionTemplateDatabaseFactory sqlSessionTemplateDatabaseFactory;

    protected abstract String getSqlMapperPrefix();

    private SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplateDatabaseFactory.getSqlSessionTemplate(SqlSessionTemplateTypeContextHolder.getSqlSessionTemplateTypeId());
    }

    public <T> T selectOne(String statement) {
        return getSqlSessionTemplate().selectOne(getSqlMapperPrefix() + statement);
    }

    public <T> T selectOne(String statement, Object parameter) {
        return getSqlSessionTemplate().selectOne(getSqlMapperPrefix() + statement, parameter);
    }

    public <E> List<E> selectList(String statement) {
        return getSqlSessionTemplate().selectList(getSqlMapperPrefix() + statement);
    }

    public <E> List<E> selectList(String statement, Object parameter) {
        return getSqlSessionTemplate().selectList(getSqlMapperPrefix() + statement, parameter);
    }

    public <K, V> Map<K, V> selectMap(String statement, Object parameter) {
        return getSqlSessionTemplate().selectMap(getSqlMapperPrefix() + statement, parameter, "id");
    }

    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        return getSqlSessionTemplate().selectMap(getSqlMapperPrefix() + statement, parameter, mapKey);
    }

    public int insert(String statement) throws Exception {
        return getSqlSessionTemplate().insert(getSqlMapperPrefix() + statement);
    }

    public int insert(String statement, Object parameter) throws Exception {
        return getSqlSessionTemplate().insert(getSqlMapperPrefix() + statement, parameter);
    }

    protected int delete(String statement) {
        return getSqlSessionTemplate().delete(getSqlMapperPrefix() + statement);
    }

    public int delete(String statement, Object parameter) throws Exception {
        return getSqlSessionTemplate().delete(getSqlMapperPrefix() + statement, parameter);
    }

    public int update(String statement) throws Exception {
        return getSqlSessionTemplate().update(getSqlMapperPrefix() + statement, null);
    }

    public int update(String statement, Object parameter) {
        return getSqlSessionTemplate().update(getSqlMapperPrefix() + statement, parameter);
    }
}
