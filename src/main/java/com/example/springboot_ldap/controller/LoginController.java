package com.example.springboot_ldap.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private LdapContextSource contextSource;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, 
                       @RequestParam String password,
                       HttpSession session) {
        try {
            // Kullanıcı DN'ini oluştur
            String userDn = "uid=" + username + ",ou=people,dc=example,dc=com";
            
            // LDAP bind denemesi
            contextSource.getContext(userDn, password);
            
            // Başarılı giriş
            session.setAttribute("username", username);
            return "redirect:/home";
        } catch (Exception e) {
            // Giriş başarısız
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
} 