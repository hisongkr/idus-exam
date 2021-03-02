package com.idus.exam.repository;

import com.idus.exam.db.base.DBRoute;
import com.idus.exam.db.config.SqlSessionTemplateType;
import com.idus.exam.db.base.RoutingBaseSqlSessionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InitRepository  extends RoutingBaseSqlSessionRepository {

    @Override
    protected String getSqlMapperPrefix() {
        return "com.idus.exam.init.";
    }


    @DBRoute(SqlSessionTemplateType.EXAM_WRITE)
    public void dropUserTable() throws Exception {
        update("dropUserTable");
    }


    @DBRoute(SqlSessionTemplateType.EXAM_WRITE)
    public void dropOrderTable() throws Exception {
        update("dropOrderTable");
    }


    @DBRoute(SqlSessionTemplateType.EXAM_WRITE)
    public void createUserTable() throws Exception {
        update("createUserTable");
    }


    @DBRoute(SqlSessionTemplateType.EXAM_WRITE)
    public void createOrderTable() throws Exception {
        update("createOrderTable");
    }
}
