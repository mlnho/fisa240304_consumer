package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class SecondClientController {

	@Autowired
	private DifferentClientLogicConsumer consumer; 

	//http://localhost:8989/secondclient
	@GetMapping("/secondclient")
	public String first() {
		System.out.println("Consumer의 first() 메소드 ***");
		
		return "(수정해봄~clinet쪽) Consumer 응답 데이터 " + consumer.getClient1ServiceLogic();
	}
	
	//? first client의 m1() 호출한 결과값을  응답
	@GetMapping("mission1")
	public String second() {
		System.out.println("Consumer의 second() 메소드 ***");
		String resData = consumer.getClient2ServiceLogic();
		log.info(resData);
		return resData;
	}

}