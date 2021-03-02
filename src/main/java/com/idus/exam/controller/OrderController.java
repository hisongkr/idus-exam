package com.idus.exam.controller;

import com.idus.exam.model.OrderInfo;
import com.idus.exam.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/addOrder")
    @ApiOperation(value = "주문입력", notes = "주문을 입려함")
    public String addOrder(@RequestBody OrderInfo orderInfo) throws Exception {
        return orderService.addOrder(orderInfo);
    }

    @GetMapping("/searchOrder")
    @ApiOperation(value = "주문조회", notes = "유저 ID를 입력받아 해당유저의 주문내역을 조회함")
    public List<OrderInfo> searchOrder(@RequestParam(value = "userId") int userId ) throws Exception {
        return orderService.searchOrder(userId);
    }

}
