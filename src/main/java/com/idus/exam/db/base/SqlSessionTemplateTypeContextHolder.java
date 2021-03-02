package com.idus.exam.db.base;


import com.idus.exam.db.config.SqlSessionTemplateType;

public class SqlSessionTemplateTypeContextHolder {
    /**
     * The Constant contenxtHolder.
     */
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * Sets the sql session template type
     *
     * @param sqlSessionTemplateType
     */
    public static void setSqlSessionTemplateTypeId(SqlSessionTemplateType sqlSessionTemplateType) {

        contextHolder.set(sqlSessionTemplateType.getId());
    }

    /**
     * Gets the sql session template type
     *
     * @return sqlSessionTemplateType
     */
    public static String getSqlSessionTemplateTypeId() {
        return contextHolder.get();
    }

    /**
     * Clear sql session templates
     */
    public static void clearSqlMapClientType() {
        contextHolder.remove();
    }
}
