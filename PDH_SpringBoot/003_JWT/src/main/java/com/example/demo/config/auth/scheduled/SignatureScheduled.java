package com.example.demo.config.auth.scheduled;

import com.example.demo.config.auth.jwt.JwtTokenProvider;
import com.example.demo.config.auth.jwt.KeyGenerator;
import com.example.demo.domain.entity.Signature;
import com.example.demo.domain.repository.SignatureRepository;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDate;
import java.util.List;

@Component
@EnableScheduling
public class SignatureScheduled {

    @Autowired
    private SignatureRepository signatureRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Scheduled(cron="0 0 0 * * *")
    public void t2() {
        System.out.println("Scheduling's t2() invoke....");
        List<Signature> list = signatureRepository.findAll();
        if(list.isEmpty()) {  //db에 저장된 key가 없으면}

            byte[] keyBytes = KeyGenerator.getKeygen();  //키 생성한 값 받아옴

            Signature signature = new Signature();  //리포지토리 생성
            signature.setKeyBytes(keyBytes);  //db에 저장
            signature.setCreate_At(LocalDate.now());
            signatureRepository.save(signature);  //리포지토리에 저장

            Key key = Keys.hmacShaKeyFor(keyBytes);  //키 암호화 시킴
            jwtTokenProvider.setKey(key);
            System.out.println("스케줄 키 인잇");

        }else{
            Signature signature = list.get(0);  //데이터를 꺼냄
            byte[] keyBytes = signature.getKeyBytes();
            signature.setKeyBytes(keyBytes);  //db에 저장
            signatureRepository.deleteAll();
            signatureRepository.save(signature);  //리포지토리에 저장

            Key key = Keys.hmacShaKeyFor(keyBytes);  //키 암호화 시킴
            jwtTokenProvider.setKey(key);
            System.out.println("스케줄 키 체인지");

        }
    }
}
