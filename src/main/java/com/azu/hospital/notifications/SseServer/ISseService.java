package com.azu.hospital.notifications.SseServer;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.notification.DestinationEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;


public interface ISseService {

    Set<SseClient> registeredClients();

    SseClient registeredClientById(Long id);

    SseEmitter registerClient();

    void removeAndLogError(SseClient client);

    void sendWelcomeToClient(SseClient client);

    void sendMessage(SseClient client, DestinationEnum event, SendNotificationDto<?> data);

    void sendMessageToAll(DestinationEnum event, SendNotificationDto<?> data);

}

@Service("SseService")
class SseService extends GenericBaseService implements ISseService {

    public static final long DEFAULT_TIMEOUT = Long.MAX_VALUE;
    private final Set<SseClient> registeredClients;
    private final BaseUserDao baseUserDao;

    public SseService(
            @Qualifier("BaseUserJpa") BaseUserDao baseUserDao,
            HttpServletRequest request,
            JwtService jwtService
    ) {
        super(jwtService, request);
        this.baseUserDao = baseUserDao;
        registeredClients = new HashSet<>();
    }

    @Override
    public Set<SseClient> registeredClients() {
        return registeredClients;
    }

    @Override
    public SseClient registeredClientById(Long id) {
        SseClient client = registeredClients().stream().filter(
                c -> c.getId() == id
        ).findFirst().orElse(null);


        return client;
    }

    @Override
    public SseEmitter registerClient() {

        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        SseClient client = new SseClient(emitter, authId());
        emitter.onCompletion(() -> registeredClients.remove(client));
        emitter.onError((err) -> removeAndLogError(client));
        emitter.onTimeout(() -> removeAndLogError(client));
        registeredClients.add(client);
        sendWelcomeToClient(client);

        return emitter;
    }

    @Override
    public void removeAndLogError(SseClient client) {
        registeredClients.remove(client);
    }

    @Override
    public void sendWelcomeToClient(SseClient client) {
        SendNotificationDto<String> data = new SendNotificationDto<>(
                "Title",
                "Content",
                "Connected",
                Instant.now()
        );
        sendMessage(client, DestinationEnum.HOSPITAL, data);
    }

    @Override
    public void sendMessage(SseClient client, DestinationEnum event, SendNotificationDto<?> data) {

        SseEmitter sseEmitter = client.getSseEmitter();

        try {
            SseEmitter.SseEventBuilder eventBuilder = event().
                    name(String.valueOf(event)).id(client.getId().toString()).
                    data(data); // MediaType.APPLICATION_JSON
            sseEmitter.send(eventBuilder);
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }
    }


    @Override
    public void sendMessageToAll(DestinationEnum event, SendNotificationDto<?> data) {
        for (SseClient client : registeredClients) {
            SseEmitter sseEmitter = client.getSseEmitter();

            try {
                SseEmitter.SseEventBuilder eventBuilder = SseEmitter.event()
                        .name(String.valueOf(event))
                        .id(client.getId().toString())
                        .data(data); // Use the provided data for the event

                sseEmitter.send(eventBuilder);
            } catch (IOException e) {
                sseEmitter.completeWithError(e);
            }
        }
    }


}





