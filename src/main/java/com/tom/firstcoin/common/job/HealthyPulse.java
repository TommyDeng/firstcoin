package com.tom.firstcoin.common.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HealthyPulse {

	@Scheduled(initialDelay = 2 * 1000, fixedRate = 20 * 60 * 1000)
	public void printPulse() {
		log.info("---^--v--^--v--^---Server Pulse---^--v--^--v--^---");
	}

}
