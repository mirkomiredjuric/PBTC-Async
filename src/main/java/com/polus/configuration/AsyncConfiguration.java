package com.polus.configuration;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration {
	
	private final static String NAME_PREFIX = "ASYNCH THREAD EXECUTOR - ";

	@Value("${core.pool.size}")
	private int corePoolSize;
	
	@Value("${max.pool.size}")
	private int maxPoolSize;
	
	@Value("${queue.capacity}")
	private int queueCapacity;
	
	@Bean("threadPoolExecutor1")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
		threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
		threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
		threadPoolTaskExecutor.setThreadNamePrefix(NAME_PREFIX);
		return threadPoolTaskExecutor;
	}
	
}
