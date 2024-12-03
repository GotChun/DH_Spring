package com.example.demo.Controller;


import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping
@Slf4j
public class SimpleController   {

    @GetMapping("/user")
    public void user(){
        log.info("user access");

    }

//	@GetMapping("/user")
//	public void user(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
//		log.info("GET /user..." + authentication);
//		log.info("name..." + authentication.getName());
//		log.info("principal..." + authentication.getPrincipal());
//		log.info("authorities..." + authentication.getAuthorities());
//		log.info("details..." + authentication.getDetails());
//		log.info("credential..." + authentication.getCredentials());
//
//        model.addAttribute("authentication", authentication);
//        model.addAttribute("principal", principalDetails);
//
//	}


//	@GetMapping("/user")
//	public void user(@AuthenticationPrincipal Principal principal) {
//		log.info("GET /user..." + principal);
//	}

    //	@GetMapping("/user")
//	public void user() {
//		log.info("GET /user...");
//
//		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication);
//
//	}
    @GetMapping("/member")
    public void member(){
        log.info("member access");
    }
    @GetMapping("/admin")
    public void admin(){
        log.info("admin access");
    }@PostMapping("/admin")
    public void admin_post(){
        log.info("admin post");
    }
    @GetMapping("/login")
    public void login(){
        log.info("login page gogo");
    }

    @GetMapping("/join")
    public void join(){
        log.info("join page gogo");
    }
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public void join_post(UserDto dto){
        log.info("join submit"+dto);

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()) )
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
    }


}
