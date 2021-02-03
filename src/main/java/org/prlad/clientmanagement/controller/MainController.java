package org.prlad.clientmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.prlad.clientmanagement.Service.ClientService;
import org.prlad.clientmanagement.Service.UserDetailService;
import org.prlad.clientmanagement.Service.UserService;
import org.prlad.clientmanagement.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class MainController {
    private Authentication auth;

    @Autowired
    UserDetailService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    ClientService clientService;


    @RequestMapping("/login")
    public String loginForm(HttpServletRequest request, Model model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("[Login Page]" + auth.getPrincipal());
        log.info("[Login Page]" + auth.getDetails());
        log.info("[Login Page]" + auth.getAuthorities());
        if (!auth.getPrincipal().equals("anonymousUser")) {
            return "/auth/home";
        }
        return "login";
    }

    @RequestMapping("/auth/home")
    public String authHome(Model model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("[client service findall] => " +userService.findByUsername(auth.getName()).get().getClientSet());

        if(auth.getAuthorities().toString().contains(Role.ROLE_ADMIN)){
            log.info("here is admin");
            model.addAttribute("users", userService.findAll());
            return "adminhome";
        }else if(auth.getAuthorities().toString().contains(Role.ROLE_MEMBER)){
            model.addAttribute("clients", userService.findByUsername(auth.getName()).get().getClientSet());
            System.out.println("[client service findall] => " + userService.findByUsername(auth.getName()).get().getClientSet());
            return "userhome";
//            return "adminhome";
        }else{
            log.error("no role here");
            return "login";
        }
    }

}