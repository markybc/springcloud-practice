package com.example.springcloud.clients;

import com.example.springcloud.clients.failback.StockServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <b></b>
 *
 * @author Yu Mengyao
 * @date 2018/11/9 13:41
 */
@FeignClient(value = "stock",fallback = StockServiceHystric.class)
public interface StockService {

	@GetMapping("/index")
	String stockIndex();


}
