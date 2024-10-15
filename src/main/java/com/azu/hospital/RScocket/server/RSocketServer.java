//package com.azu.hospital.RScocket.server;
//import io.rsocket.*;
//import io.rsocket.transport.netty.server.CloseableChannel;
//import io.rsocket.transport.netty.server.TcpServerTransport;
//import io.rsocket.util.DefaultPayload;
//import reactor.core.publisher.Mono;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//public class RSocketServer {
//    private static final Map<String, RSocket> doctorConnections = new ConcurrentHashMap<>();
//
//    public static void main(String[] args) {
//        startRSocketServer();
//    }
//
//    public static void startRSocketServer() {
//        RSocket rSocket = new AbstractRSocket() {
//            @Override
//            public Mono<Void> fireAndForget(Payload payload) {
//                String clientId = payload.getMetadataUtf8();
//                    Long doctorId = Long.parseLong(clientId);
//                    notifyDoctor(doctorId, payload.getDataUtf8());
//                return Mono.empty();
//            }
//        };
//
//        SocketAcceptor responder = (setup, sendingSocket) -> {
//            String clientId = setup.getMetadataUtf8();
//                // Doctor connection
//                Long doctorId = Long.parseLong(clientId);
//                doctorConnections.put(String.valueOf(doctorId), sendingSocket);
//            return Mono.just(rSocket);
//        };
//
//        CloseableChannel server = RSocketFactory.receive()
//                .acceptor(responder)
//                .transport(TcpServerTransport.create("localhost", 7000))  // Choose your desired port
//                .start()
//                .block();
//
//        // Block the main thread to keep the server running
//        server.onClose().block();
//    }
//    public static void notifyDoctor(Long doctorId, String notification) {
//        RSocket doctorRSocket = doctorConnections.get(String.valueOf(doctorId));
//        if (doctorRSocket != null) {
//            doctorRSocket.fireAndForget(DefaultPayload.create(notification)).subscribe();
//        }
//    }
//
//}
