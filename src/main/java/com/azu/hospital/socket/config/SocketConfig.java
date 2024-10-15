//package com.azu.hospital.socket.config;
//
//
//import com.azu.hospital.security.newsecurity.config.JwtService;
//import com.corundumstudio.socketio.Configuration;
//import com.corundumstudio.socketio.SocketIOClient;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.listener.ConnectListener;
//import com.corundumstudio.socketio.listener.DisconnectListener;
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//
//@CrossOrigin
//@Component
//@Log4j2
//public class SocketConfig {
//
//    @Value("${socket.host}")
//    private String SOCKETHOST;
//
//    @Value("${socket.port}")
//    private int SOCKETPORT ;
//
//    private  SocketIOServer server;
//
//    private final JwtService jwtService;
//
//    @Autowired
//    public SocketConfig( JwtService jwtService) {
//        this.jwtService = jwtService;
//    }
//
//    @Bean
//    public SocketIOServer socketIOServer(){
//        Configuration config = new Configuration();
//        config.setHostname(SOCKETHOST);
//        config.setPort(SOCKETPORT);
//        server = new SocketIOServer(config);
//        server.start();
//        server.addConnectListener(new ConnectListener() {
//
//            @Override
//            public void onConnect(SocketIOClient client) {
//                log.info(client.getHandshakeData().getHttpHeaders().get("Authorization") + " new user connected with " +
//                        "socket " + client.getSessionId());
//            }
//        });
//
//        server.addDisconnectListener(new DisconnectListener() {
//            @Override
//            public void onDisconnect(SocketIOClient client) {
//                client.getNamespace().getAllClients().stream().forEach(
//                        data-> {
//                    log.info("user disconnected "+data.getSessionId().toString());});
//            }
//        });
//
//        server.addConnectListener(client -> {
//            client.joinRoom("publicRoom");
//        });
//
//
//
//        return server;
//
//    }
//
//    @PreDestroy
//    public void stopSocketIOServer() {
//         this.server.stop();
//    }
//
//
//
//}
