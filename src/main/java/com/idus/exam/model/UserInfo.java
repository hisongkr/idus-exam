package com.idus.exam.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfo {
    @ApiModelProperty(value = "유저ID")
    private Integer userId;

    @ApiModelProperty(value = "유저성명")
    private String userName;
    @ApiModelProperty(value = "유저별명")
    private String userNickname;

    @ApiModelProperty(value = "Password")
    private String userPassword;
    @ApiModelProperty(value = "전화번호")
    private String userTel;
    @ApiModelProperty(value = "이메일")
    private String userEmail;
    @ApiModelProperty(value = "성별")
    private String userGender;

    @ApiModelProperty(value = "최근주문번호")
    private String lastOrderNo;

    @ApiModelProperty(value = "최근 주문상품명")
    private String lastGoodsName;

    @ApiModelProperty(value = "최근주문일시")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastPaymentDate;

}
