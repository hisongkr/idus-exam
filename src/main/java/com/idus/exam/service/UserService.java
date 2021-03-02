package com.idus.exam.service;

import com.idus.exam.model.LoginUserInfo;
import com.idus.exam.model.UserInfo;
import com.idus.exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {


    private final LoginUserInfo loginUserInfo;
    private final UserRepository userRepository;

    @Transactional
    public String loginUser(UserInfo userInfo ,  String userSession) throws Exception {


        UserInfo dbUserInfo = userRepository.selectUserInfo(userInfo);
        if(dbUserInfo == null){
            return null;
        }

        if(dbUserInfo.getUserPassword().equals(userInfo.getUserPassword())){

            loginUserInfo.addUserLoginInfo(userInfo.getUserEmail(),userSession);
        } else {
            return null;
        }

        return userSession;
    }


    public UserInfo selectUserInfo(UserInfo userInfo ) throws Exception {

        return userRepository.selectUserInfo(userInfo);
    }

    public boolean isLogin(String userEmail, String userSession){

        String serverSession = loginUserInfo.searchLoginUser(userEmail);
        if(userSession.equals(serverSession)){
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean logoutUser(String userEmail, String userSession){

        String serverSession = loginUserInfo.searchLoginUser(userEmail);
        if(userSession.equals(serverSession)){
            loginUserInfo.delUserLoginInfo(userEmail);
            return true;
        } else {
            return false;
        }

    }

    public int loginUserCnt(){

        return loginUserInfo.getLoginUserCnt();
    }

    @Transactional
    public int signupUser(UserInfo userInfo) throws Exception {
        //입력한 Password가 적절한지 검사
        if(isValidPassword(userInfo.getUserPassword()) > 1){
            return 0;
        }
        //입력한 전화번호가 숫자가 맞는지 검사
        if(isValidTel(userInfo.getUserTel()) > 1){
            return 0;
        }

        //입력한 이름 숫자가 맞는지 검사
        if(isValidName(userInfo.getUserName()) > 1){
            return 0;
        }

        //입력한 별명 숫자가 맞는지 검사
        if(isValidNickname(userInfo.getUserNickname()) > 1){
            return 0;
        }
        //입력한 Email 맞는지 검사
        if(isValidEmail(userInfo.getUserEmail()) > 1){
            return 0;
        }

        //Email 중복 체크
        UserInfo dbUserInfo = userRepository.selectUserInfo(userInfo);
        if(dbUserInfo == null){
            return userRepository.insertUserInfo(userInfo);
        } else {
            return 0;
        }
    }

    public List<UserInfo> searchUserList(String userName, String userEmail, int page, int count) throws Exception {
        return userRepository.selectUserList(userName, userEmail,page, count);
    }

    public static int isValidPassword(String password) {
        // 최소 8자, 최대 20자 상수 선언
        final int MIN = 10;
        final int MAX = 20;

        // 영어, 숫자, 특수문자 포함한 MIN to MAX 글자 정규식 A-Z
        final String REGEX = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{" + MIN + "," + MAX + "})$";

        // 공백 체크
        if (password == null || "".equals(password)) {
            return 1;
        }

        // 문자열 길이
        int strLen = password.length();
        // 글자 길이 체크
        if (strLen < MIN || strLen > MAX) {
            return 2;
        }


        // 비밀번호 정규식 체크
        if (!password.matches(REGEX)) {
            return 3;
        }
        return 0;
    }


    public static int isValidName(String name) {
        final int MAX = 20;
        // 공백 체크
        if (name == null || "".equals(name)) {
            return 1;
        }
        // 길이
        if (name.length() > MAX) {
            return 2;
        }

        //한글, 영문대문자만 허용
        if(name.matches("^[ㄱ-ㅎ가-힣A-Z]*$")){
            return 0;
        } else {
            return 3;
        }

    }

    public static int isValidNickname(String nickname) {

        final int MAX = 30;
        // 공백 체크
        if (nickname == null || "".equals(nickname)) {
            return 1;
        }
        // 길이
        if (nickname.length() > MAX) {
            return 2;
        }
        //영문소문자만 허용
        if(nickname.matches("^[a-z]*$")){
            return 0;
        } else {
            return 3;
        }

    }

    public static int isValidTel(String tel) {
        final int MAX = 20;
        // 공백 체크
        if (tel == null || "".equals(tel)) {
            return 1;
        }
        // 길이
        if (tel.length() > MAX) {
            return 2;
        }

        try {
            Integer.parseInt(tel);
            return 0;
        } catch(NumberFormatException e) {
            return 1;
        }
    }


    public static int isValidEmail(String email) {
        final int MAX = 100;
        // 공백 체크
        if (email == null || "".equals(email)) {
            return 1;
        }
        // 길이
        if (email.length() > MAX) {
            return 2;
        }
        //이메일만 허용
        if (email.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?")) {
            return 0;
        } else {
            return 3;
        }
    }


}
