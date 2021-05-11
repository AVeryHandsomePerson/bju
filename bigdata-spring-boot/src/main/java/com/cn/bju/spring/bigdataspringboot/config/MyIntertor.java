package com.cn.bju.spring.bigdataspringboot.config;

import com.bjucloud.sso.dto.TokenRequest;
import com.bjucloud.usercenter.dto.UserLoginDTO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author dell
 * @Auther: ljh
 * @Date: 2021/1/17 14:54
 * @Description:
 */
public class MyIntertor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserLoginDTO userLoginDTO = (UserLoginDTO) session.getAttribute("token");
        System.out.println(userLoginDTO.getLoginToken());
        try {
            if (!Objects.isNull(userLoginDTO.getLoginToken())){
                return true;
            }else{
                response.sendRedirect("http://sso.serviceent.com/view/login.html");
            }
        }catch (NullPointerException e){
            response.sendRedirect("http://sso.serviceent.com/view/login.html");
        }
        return false;
    }
}
