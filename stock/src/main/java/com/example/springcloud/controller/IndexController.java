package com.example.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b></b>
 *
 * @author Yu Mengyao
 * @date 2018/11/9 11:12
 */
@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping("index")
	public String index(){
		try {
			System.out.print("s");
			Thread.sleep(1000L);
			System.out.print("e");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "stock";
	}


}
