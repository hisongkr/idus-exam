package com.idus.exam.controller;

import com.idus.exam.model.UserInfo;
import com.idus.exam.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Api("User Controller API")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "회원 로그인", notes = "userEmail, userPassword의 값만 전달해주면 됨")
    public String login(@RequestBody @ApiParam(value = "유저정보", hidden = true)  UserInfo userInfo,
                        //@ApiParam(value = "세션정보", hidden = true) HttpSession session //Swagger에서 session 정보의 모든 속성을 보여줘서 우선 String으로  SessionID받는 것을 변경
                        @ApiParam(name = "sessionId", value = "HttpSession의 Id") String sessionId
    ) throws Exception {

        //return userService.loginUser(userInfo, session.getId());
        return userService.loginUser(userInfo, sessionId);

    }

    @GetMapping("/isLogin")
    @ApiOperation(value = "회원 로그인 여부확인", notes = "userEmail을 입력하면 해당유저의 RequestSession을 확인하여 로그인 여부를 True/False로 Return")
    public boolean isLogin(@RequestParam(value = "userEmail") String userEmail,
                           //@ApiParam(value = "세션정보", hidden = true) HttpSession session //Swagger에서 session 정보의 모든 속성을 보여줘서 우선 String으로  SessionID받는 것을 변경
                           @ApiParam(name = "sessionId", value = "HttpSession의 Id") String sessionId
    ) throws Exception {


       //return userService.isLogin(userEmail, session.getId());
        return userService.isLogin(userEmail, sessionId);
    }

    @PostMapping("/logOut")
    @ApiOperation(value = "회원 로그아웃", notes = "userEmail의 값만 전달해주면, 해당 세션의 유저에 대하여 로그아웃")
    public Boolean logOut(@RequestBody UserInfo userInfo,
                          //@ApiParam(value = "세션정보", hidden = true) HttpSession session //Swagger에서 session 정보의 모든 속성을 보여줘서 우선 String으로  SessionID받는 것을 변경
                          @ApiParam(name = "sessionId", value = "HttpSession의 Id") String sessionId
    ) throws Exception {


        //return userService.logoutUser(userInfo.getUserEmail(), session.getId());
        return userService.logoutUser(userInfo.getUserEmail(), sessionId);

    }

    @PostMapping("/signupUser")
    @ApiOperation(value = "회원 가입", notes = "신규회원가입")
    public Integer signupUser(@RequestBody UserInfo userInfo) throws Exception {

        return userService.signupUser(userInfo);

    }


    @GetMapping("/searchUserList")
    @ApiOperation(value = "회원유저목록조회", notes = "여러명의 유저를 페이지단위로 조회")
    public List<UserInfo> searchUserList(@RequestParam(value = "userEmail", required = false)
                                            @ApiParam(value = "조회조건이될 이메일", required = false) String userEmail ,
                                         @RequestParam(value = "userName", required = false)
                                            @ApiParam(value = "조회조건이될 유저명", required = false) String userName ,
                                         @RequestParam(value = "page")
                                             @ApiParam(value = "조회할 페이지") int page,
                                         @RequestParam(value = "count")
                                             @ApiParam(value = "한페이지에 표시할 레코드 수")int count) throws Exception {

        return userService.searchUserList(userName, userEmail, page, count);
    }


    @GetMapping("/searchUserInfo")
    @ApiOperation(value = "회원유저상세조회", notes = "한명의 유저 정보를 조회")
    public UserInfo searchUserInfo(@RequestParam(value = "userId")
                                       @ApiParam(value = "유저아이디") int userId ) throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        return userService.selectUserInfo(userInfo);
    }


    @GetMapping("/loginUserCnt")
    @ApiOperation(value = "로그인 유저수 조회", notes = "현재 메모리에 로그인되어있는 유저의 수를 조회")
    public int loginUserCnt(){

        return userService.loginUserCnt();
    }

}
