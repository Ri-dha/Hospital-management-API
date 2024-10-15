package com.azu.hospital.notifications.SseServer;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Getter
public class SseClient {
    private  SseEmitter sseEmitter;

    private final Long id;

    private  BaseUser user;

    public SseClient(SseEmitter sseEmitter, Long id) {
        this.sseEmitter = sseEmitter;
        this.id = id;
    }
    public SseClient(SseEmitter sseEmitter, Long id ,BaseUser user){
        this.sseEmitter = sseEmitter;
        this.id = id;
        this.user = user;
    }

    public SseClient(Long id) {
        this.id = id;
    }
}