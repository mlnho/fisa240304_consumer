package com.example.demo;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;

//사용 가능한 spring bean instance
/* spring bean
 * 	- spring library (framework) 내부에 spring container 에서 생성, 관리, 소멸하는 객체들 의미
 * 	- new로 생성하는 일반 자바 객체와 달리 spring 자체가 관리하는 객체들 의미
 * 
 * @Component
 *  - spring bean 의미
 * 	- spring bean 들에 한해서만 다른 클래스에서 (spring bean) 에서 spring 개발 형식으로 사용 가능한 객체	
 * 	-  @Autowired
 * 		private DifferentClientLogicConsumer consumer;
 * 		: spirng에게 DifferentClientLogicConsumer 객체 멤버 변수로 초기화 해주세요 요청!
 * 
 */
@Component
public class DifferentClientLogicConsumer {

	@Autowired
	private DiscoveryClient client;
	
	/* 1.PRODUCER-SERVICE 문구로 매핑한 설정 정보 
	 * 	- 어디 ?
	 * 2. instance 용어
	 * 	- java : 사용 가능한 메모리에 생성된 객체
	 * 	- aws : ec2 와 같은 사용 가능한 장비 + OS
	 * 3. firstclient url 의미
	 * 	- 어디?
	 * 4. 처음보는 api 기능 파악
	 * 
	 */
	public String getClient2ServiceLogic() {
		//server에 등록된 PRODUCER-SERVICE 이름의 다른 client 검색
		List<ServiceInstance> siList = client.getInstances("PRODUCER-SERVICE");
		ServiceInstance si = siList.get(0);
		
		String url = si.getUri() + "/mission1";  
		
		return new RestTemplate().getForObject(url, String.class);
	}


	public String getClient1ServiceLogic() {
		List<ServiceInstance> siList = client.getInstances("PRODUCER-SERVICE");

		ServiceInstance si = siList.get(0);
		
		System.out.println("두번째 client 요청 처리 " + si);

		String url = si.getUri() + "/firstclient";
		System.out.println(url);
		RestTemplate rt = new RestTemplate();

		String response = rt.getForObject(url, String.class);
		return response;
	}
}