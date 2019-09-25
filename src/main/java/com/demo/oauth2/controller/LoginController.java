
package com.demo.oauth2.controller;

import com.demo.oauth2.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {
    
    @Autowired
    private  LoginService loginService;
    
    @GetMapping(value = "/")
    @ResponseBody
    public RedirectView loginPage(){
        RedirectView redirectView = new RedirectView();
        System.out.println("asdfasdf");
        String url = this.loginService.loginFacebook();
        redirectView.setUrl(url);
        System.out.println(url);
        return redirectView;
    }
    
    @GetMapping("/facebook")
    @ResponseBody
    public String callBackUrl(@RequestParam("code") String code){
        System.out.println("callBackUrl");
        String tooken = this.loginService.getAccessFacebookToken(code);
        System.out.println(tooken);
        return "callBackUrl";
    }
}
