package com.elk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.elk.exception.TxnExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

@RestController
public class ELKController {
	
	private static final Logger log = LoggerFactory.getLogger(ELKController.class);
	
	
	
	@RequestMapping(value = "/elk")
	public String helloWorld() {
		String response = "Welcome to JavaInUse" + new Date();
		log.info(response, Level.INFO);

		return response;
	}
	
	@RequestMapping(value = "/exception")
	public String exception() {
		String response = "";
		try {
			throw new Exception("Exception has occured....");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stackTrace = sw.toString();
			log.error("Exception - " + stackTrace);
			response = stackTrace;
		}

		return response;
	}
	
	@Scheduled(fixedDelay = 2000)
	public void scheduleFixedDelayTask() {
	    System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
	}
	
	@EventListener({ApplicationStartedEvent.class, TxnExceptionHandler.class})
	public void getAllSignal() {		
		log.info("hello world");		
	}
}
