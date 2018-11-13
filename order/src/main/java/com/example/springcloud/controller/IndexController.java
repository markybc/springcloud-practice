package com.example.springcloud.controller;

import com.example.springcloud.clients.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b></b>
 *
 * @author Yu Mengyao
 * @date 2018/11/9 11:12
 */
@RefreshScope
@RestController
@RequestMapping("/")
public class IndexController {

	@Autowired
	private StockService stockService;

	@Value("${app}")
	private String app;

	@GetMapping("index")
	public String index() {
		return "order";
	}

	@GetMapping("app")
	public String app() {
		return app;
	}

	@GetMapping("stock")
	public String stock() {
		return stockService.stockIndex();
	}

}
