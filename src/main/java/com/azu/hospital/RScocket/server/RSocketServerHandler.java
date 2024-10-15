//package com.azu.hospital.RScocket.server;
//
//import io.rsocket.*;
//import io.rsocket.util.DefaultPayload;
//import reactor.core.publisher.Mono;
//
//public class RSocketServerHandler {
//    private RSocket clientRSocket; // Store the client's RSocket
//
//    public Mono<RSocket> accept(ConnectionSetupPayload setup, RSocket sendingSocket) {
//        this.clientRSocket = sendingSocket;
//        return Mono.just(new AbstractRSocket() {
//            @Override
//            public Mono<Void> fireAndForget(Payload payload) {
//                // Handle notification from the client
//                System.out.println("Received notification from client: " + payload.getDataUtf8());
//                return Mono.empty();
//            }
//        });
//    }
//
//    public void sendNotification(String notification) {
//        if (clientRSocket != null) {
//            clientRSocket.fireAndForget(DefaultPayload.create(notification)).subscribe();
//        }
//    }
//}
//
