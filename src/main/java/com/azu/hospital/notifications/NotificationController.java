package com.azu.hospital.notifications;

import com.azu.hospital.notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

//    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationController(NotificationService notificationService
//            , SimpMessagingTemplate messagingTemplate
    ) {
        this.notificationService = notificationService;
//        this.messagingTemplate = messagingTemplate;
    }


//    public void notifyUser(String username, String message) {
//        messagingTemplate.convertAndSendToUser(username, "/queue/notify", message);
//    }
//
//    // Endpoint to receive a message and send it to a specific topic.
//    @MessageMapping("/sendMessage")
//    public void sendMessage(@Payload String message) {
//        this.messagingTemplate.convertAndSend("/topic/public", message);
//    }

    // Endpoint to subscribe to and receive messages from.
    @SubscribeMapping("/subscribe")
    public String subscribe() {
        // Initial message or status when the client subscribes
        return "Connected to WebSocket!";
    }
}
