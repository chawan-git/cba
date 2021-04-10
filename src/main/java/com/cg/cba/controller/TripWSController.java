package com.cg.cba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


import com.cg.cba.entities.TripBooking;


@Controller
public class TripWSController {
	
	@Autowired 
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/webSocket")
    public void processMessage(@Payload TripBooking tripBooking) {        
        messagingTemplate.convertAndSendToUser(
                tripBooking.getCustomer().getUsername(),"/customer",
                tripBooking);

        messagingTemplate.convertAndSendToUser(
                tripBooking.getDriver().getUsername(),"/driver",
                tripBooking);
        

    }
	
}
