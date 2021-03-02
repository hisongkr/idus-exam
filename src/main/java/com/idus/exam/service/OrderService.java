package com.idus.exam.service;

import com.idus.exam.model.OrderInfo;
import com.idus.exam.model.UserInfo;
import com.idus.exam.repository.InitRepository;
import com.idus.exam.repository.OrderRepository;
import com.idus.exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public String addOrder(OrderInfo orderInfo) throws Exception {

        //주문정보 삽입
        String strOrderNo = orderRepository.insertOrderInfo(orderInfo);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(orderInfo.getUserId());
        userInfo.setLastGoodsName(orderInfo.getGoodsName());
        userInfo.setLastOrderNo(strOrderNo);
        userInfo.setLastPaymentDate(orderInfo.getPaymentDate());

        //최근 주문 정보업데이트
        userRepository.updateUserLastOrder(userInfo);

        return strOrderNo;
    }


    public List<OrderInfo> searchOrder(int userId) throws Exception {

        return orderRepository.selectOrderList(userId);

    }
}
