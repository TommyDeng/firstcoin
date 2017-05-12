package com.tom.firstcoin.common.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tom.firstcoin.service.AntennaService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AntennaJob {
	@Autowired
	AntennaService antennaService;

	@Scheduled(initialDelay = 2 * 1000, fixedRate = 5 * 60 * 1000)
	public void antenna() {
		log.info("Antenna Job start.");

		try {
			antennaService.executeHenzan();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.info("Antenna Job end.");
	}

//	@Scheduled(initialDelay = 60 * 1000, fixedRate = 5 * 24 * 60 * 60 * 1000)
	public void historyBackup() {
		log.info("History Backup Job start.");

		try {
			antennaService.backupHistory();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.info("History Backup Job end.");
	}
}
