package com.idus.exam.repository;

import com.idus.exam.db.base.DBRoute;
import com.idus.exam.db.base.RoutingBaseSqlSessionRepository;
import com.idus.exam.db.config.SqlSessionTemplateType;
import com.idus.exam.model.OrderInfo;
import com.idus.exam.model.UserInfo;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Repository
@MappedTypes(LocalDateTime.class)
public class OrderRepository extends RoutingBaseSqlSessionRepository {

    @Override
    protected String getSqlMapperPrefix() {
        return "com.idus.exam.order.";
    }


    @DBRoute(SqlSessionTemplateType.EXAM_READ)
    public List<OrderInfo> selectOrderList(int userId) throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("userId", userId);
        return selectList("selectOrderInfo", param);

    }

    @DBRoute(SqlSessionTemplateType.EXAM_WRITE)
    public String insertOrderInfo(OrderInfo orderInfo) throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("goodsName", orderInfo.getGoodsName());
        //param.put("paymentDate", orderInfo.getPaymentDate().toLocalDate().toString() + " " + orderInfo.getPaymentDate().toLocalTime().toString() );
        param.put("paymentDate", orderInfo.getPaymentDate());
        param.put("userId", orderInfo.getUserId());

        Date today = new Date();
        SimpleDateFormat fDate = new SimpleDateFormat("MMyy-", Locale.ENGLISH);
        String keyHeader = fDate.format(today);

        param.put("key", keyHeader);;
//        orderInfo.setKey(keyHeader);

        insert("insertOrderInfo", param);
        return (String)param.get("orderNo");

    }

}
