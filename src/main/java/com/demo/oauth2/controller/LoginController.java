package com.demo.oauth2.controller;

import com.demo.oauth2.service.LoginService;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/api/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/fb")
    public RedirectView loginPage(HttpServletResponse response) throws IOException {
        System.out.println("Asdfasdfasdf");
        String url = this.loginService.loginFacebook();

        System.out.println(url);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }
//    @GetMapping(value = "/fb")
//    @ResponseBody
//    public Map<String, Object> loginPage(HttpServletResponse response) throws IOException {
//        System.out.println("Asdfasdfasdf");
//        String url = this.loginService.loginFacebook();
//
//        System.out.println(url);
//        RedirectView redirectView = new RedirectView();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("url", url);
//        return map;
//    }

    @GetMapping("/facebook")
    public String callBackUrl(@RequestParam("code") String code) {
        System.out.println("callBackUrl");
        String tooken = this.loginService.getAccessFacebookToken(code);
        System.out.println(tooken);
        return "redirect:/profiledata/"+tooken;
    }
}
