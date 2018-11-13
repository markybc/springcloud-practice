package com.example.springcloud.clients.failback;

import com.example.springcloud.clients.StockService;
import org.springframework.stereotype.Component;

/**
 * <b></b>
 *
 * @author Yu Mengyao
 * @date 2018/11/9 14:29
 */
@Component
public class StockServiceHystric implements StockService {
	@Override
	public String stockIndex() {
		return "stock fail";
	}
}
