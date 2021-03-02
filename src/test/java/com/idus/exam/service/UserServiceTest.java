package com.idus.exam.service;

import com.idus.exam.model.OrderInfo;
import com.idus.exam.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Test
    void 메인테스트() throws Exception {

        //회원가입
        UserInfo userInfo = new UserInfo();
        for(int i = 0; i<100 ; i++){
            userInfo.setUserName("TEST유저들");
            userInfo.setUserEmail("TestUser" + i + "@test.com");
            userInfo.setUserNickname("nickname");
            userInfo.setUserPassword("Password!@" + i );
            userInfo.setUserTel("010"+String.format("%08d",i));
            if(i%2 == 1){
                userInfo.setUserGender("M");
            } else {
                userInfo.setUserGender("F");
            }
            userService.signupUser(userInfo);
        }

        //로그인
        UserInfo loginUserInfo = new UserInfo();
        for(int i = 0; i<100 ; i++){
            loginUserInfo.setUserEmail("TestUser" + i + "@test.com");
            loginUserInfo.setUserPassword("Password!@" + i );
            userService.loginUser(loginUserInfo,"testSessionString");

            assertEquals(true, userService.isLogin("TestUser" + i + "@test.com","testSessionString"));
            int cnt = userService.loginUserCnt();
            System.out.println(loginUserInfo.getUserEmail());
            assertEquals(i+1, cnt);
        }

        //로그아웃
        for(int i = 99; i>-1 ; i--){
            userService.logoutUser("TestUser" + i + "@test.com","testSessionString");

            int cnt = userService.loginUserCnt();
            assertEquals(i, cnt);
        }


        //주문 1인당 3건씩 주문하기
        OrderInfo orderInfo = new OrderInfo();
        LocalDateTime paymentDate = LocalDateTime.now();

        UserInfo searchUserInfo = new UserInfo();
        for(int i = 1; i<=100 ; i++){
            for(int j=0; j<3; j++ ){
                orderInfo.setPaymentDate(paymentDate);
                orderInfo.setGoodsName("Merry♥️"+j+"___"+i);
                orderInfo.setUserId(i);
                orderService.addOrder(orderInfo);
            }
            searchUserInfo.setUserId(i);
            UserInfo resultUserInfo = userService.selectUserInfo(searchUserInfo);

            assertEquals(i, resultUserInfo.getUserId());

            List<OrderInfo> dbOrderList = orderService.searchOrder(i);
            assertEquals(3, dbOrderList.size());

        }


        List<UserInfo> dbUserList = userService.searchUserList(null,null, 2,20);

        assertEquals(21, dbUserList.get(0).getUserId());

    }
}