package com.idus.exam.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
public class LoginUserInfo {

    private Map<String, String> loginUserInfo;

    public LoginUserInfo() {
        this.loginUserInfo = new ConcurrentHashMap<>();
    }

    public void addUserLoginInfo(String email, String sesstionId){
        if(loginUserInfo.get(email) == null){
            loginUserInfo.put(email, sesstionId);
        } else {
            loginUserInfo.replace(email, sesstionId);
        }
    }

    public void delUserLoginInfo(String email){
        if(loginUserInfo.get(email) != null){
            loginUserInfo.remove(email);
        }
    }


    public String searchLoginUser(String email){
        return loginUserInfo.get(email);
    }

    public int getLoginUserCnt(){
        return loginUserInfo.size();
    }


}
