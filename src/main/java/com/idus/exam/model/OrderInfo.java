package com.idus.exam.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "주문 정보", description = "주문 요청시 주문번호는 입력하지 않아야 함")

public class OrderInfo {
    @ApiModelProperty(value = "주문번호", required = false)
    private String orderNo;
    @ApiModelProperty(value = "상품명",required = true)
    private String goodsName;
    @ApiModelProperty(value = "주문자 회원번호", required = true)
    private Integer userId;
    @ApiModelProperty(value = "key"
            , required = false)
    private String key;

    @ApiModelProperty(value = "결재 시간", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime paymentDate;


}


