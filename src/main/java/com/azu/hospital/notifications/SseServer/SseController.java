package com.azu.hospital.notifications.SseServer;

import com.azu.hospital.utils.enums.notification.DestinationEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Instant;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class SseController {

    private final ISseService sseService;

    public SseController(
            ISseService sseService
    ) {
        this.sseService = sseService;
    }
    /* TODO: 1/10/2024 name of method camel case */

    @GetMapping(path = "/sse-register" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter RegisterUser(){
        return sseService.registerClient();
    }

//    @GetMapping(path = "/sse-register/{fromUser}/{toUser}")
//    public void SendMessageToClient(@PathVariable Long fromUser ,@PathVariable Long toUser){
//        SseClient client = sseService.registeredClientById(toUser);
//        SendNotificationDto<String> data = new SendNotificationDto<String>(
//                "Title" ,
//                "Content" ,
//                "message" ,
//                Instant.now()
//        );
//        sseService.sendMessage(client , DestinationEnum.HOSPITAL , data);
//    }
//
//
//    @GetMapping("sendToEvent")
//    public void sendMessage(@RequestParam("event") String event ){
//
//        SendNotificationDto<String> sendNotificationDto = new SendNotificationDto<String>(event , event , event ,
//                Instant.now());
//        sseService.sendMessageToAll(DestinationEnum.HOSPITAL , sendNotificationDto);
//    }
//
//    @PostMapping("/send_message")
//    public void sendMessage(@RequestParam("event") String event , @RequestParam("id") Long id){
//        SseClient client = sseService.registeredClientById(id);
//        SendNotificationDto<String> sendNotificationDto = new SendNotificationDto<String>(event , event , event ,
//                Instant.now());
//        sseService.sendMessage(client , DestinationEnum.HOSPITAL , sendNotificationDto);
//    }

}
