package com.example.demo.C06Google;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/google")
public class C01GoogleMailAPIController {

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/mail/req")
    public void req(
            @RequestParam("email") String email,
            @RequestParam("text") String text){
        log.info("GET 구글 이메일 텍스트.."+email +"text" + text);
        log.info("javaMailSender : " + javaMailSender);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("홀리 몰리");
        message.setText(text);

        javaMailSender.send(message);



    }



}
