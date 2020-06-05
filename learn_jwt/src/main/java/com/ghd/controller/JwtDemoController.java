package com.ghd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ghd.annocation.UserLoginToken;
import com.ghd.model.User;
import com.ghd.service.UserService;
import com.ghd.utils.JwtUtil;
import com.ghd.wrapper.UserWrapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/6 16:43
 */
@RestController
@RequestMapping("/login")
public class JwtDemoController {

    @Autowired
    private UserService userService;


    //登录
    @PostMapping("/loginUser")
    public Object login(User user){
        JSONObject jsonObject=new JSONObject();
        User userInfo = userService.findUserUnmPwd(user.getUsername(),user.getPassword());
        if(Objects.isNull(userInfo)){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            //String token = tokenService.getToken(userInfo);
            String token = JwtUtil.createJWT(userInfo);
            jsonObject.put("token", token);
            jsonObject.put("user", userInfo);
            return jsonObject;
        }

        /*JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findByUsername(user);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }*/
    }


    /**
     * 必须带token才能访问
     * @return
     */
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(HttpServletRequest request){
        String token = request.getHeader("token");
        Claims claims = JwtUtil.parseJWT(token);
        String s = JSON.toJSONString(claims);
        UserWrapper userWrapper = JSONObject.parseObject(s, UserWrapper.class);
        return userWrapper.toString();
    }
}
