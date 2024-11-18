package com.example.ex01.schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Scheduling {
	
//	@Scheduled(fixedRate = 1000)
//	public void t1() {
//		System.out.println("Scheduling's t1() invoke....");
//	}

	@Scheduled(cron="*/15 * * * * *")
	public void t2() {
		System.out.println("Scheduling's t2() invoke....");
	}
	
}
//초(0-59)  분(0-59)  시(0-23)  일(1-31)  월(1-12)  요일(0-6)
//매 초마다 실행: "* * * * * *"
//매 시간의 30분마다 실행: "0 30 * * * *"
//매일 오전 8시 30분마다 실행: "0 30 8 * * *"
//매주 월요일 아침 9시 30분마다 실행: "0 30 9 * * 1"
//매월 1일 아침 4시 30분마다 실행: "0 30 4 1 * *"
//매주 월요일부터 금요일까지 아침 10시마다 실행: "0 0 10 * * 1-5"