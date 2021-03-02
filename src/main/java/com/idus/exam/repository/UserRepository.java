package com.idus.exam.repository;

import com.idus.exam.db.base.DBRoute;
import com.idus.exam.db.base.RoutingBaseSqlSessionRepository;
import com.idus.exam.db.config.SqlSessionTemplateType;
import com.idus.exam.model.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository extends RoutingBaseSqlSessionRepository {

    @Override
    protected String getSqlMapperPrefix() {
        return "com.idus.exam.user.";
    }

    @DBRoute(SqlSessionTemplateType.EXAM_READ)
    public UserInfo selectUserInfo(UserInfo userInfo) throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("userId", userInfo.getUserId());
        param.put("userEmail", userInfo.getUserEmail());
        return selectOne("selectUserInfo", param);

    }

    @DBRoute(SqlSessionTemplateType.EXAM_READ)
    public List<UserInfo> selectUserList(String userName, String userEmail, int page, int count) throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("userName", userName);
        param.put("userEmail", userEmail);
        param.put("page", (page-1) * count);
        param.put("rowCount", count);
        return selectList("selectUserInfoList", param);

    }


    @DBRoute(SqlSessionTemplateType.EXAM_WRITE)
    public int insertUserInfo(UserInfo userInfo) throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("userEmail", userInfo.getUserEmail());
        param.put("userGender", userInfo.getUserGender());
        param.put("userName", userInfo.getUserName());
        param.put("userNickname", userInfo.getUserNickname());
        param.put("userPassword", userInfo.getUserPassword());
        param.put("userTel", userInfo.getUserTel());

        insert("insertUserInfo", param);
        return (Integer) param.get("userId");

    }

    @DBRoute(SqlSessionTemplateType.EXAM_WRITE)
    public int updateUserLastOrder(UserInfo userInfo) throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("userId", userInfo.getUserId());
        param.put("lastOrderNo", userInfo.getLastOrderNo());
        param.put("lastGoodsName", userInfo.getLastGoodsName());
        param.put("lastPaymentDate", userInfo.getLastPaymentDate());

        insert("updateUserOrderInfo", param);
        return (Integer) param.get("userId");

    }
}
