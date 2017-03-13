package com.tom.firstcoin.common.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tom.firstcoin.service.AntennaService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HealthyPulse {

	@Autowired
	AntennaService antennaService;

	@Scheduled(initialDelay = 2000, fixedRate = 1200000)
	public void printPulse() {
		log.info("Server Pulse.");
	}

	@Scheduled(initialDelay = 2000, fixedRate = 300000)
	public void antenna() {
		log.info("Antenna Job start.");

		try {
			antennaService.executeHenzan();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.info("Antenna Job end.");
	}

}
