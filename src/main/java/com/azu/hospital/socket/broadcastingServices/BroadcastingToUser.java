//package com.azu.hospital.socket.broadcastingServices;
//
//import com.azu.hospital.socket.payload.SendMessageBroadcasting;
//import com.corundumstudio.socketio.SocketIOServer;
//import org.springframework.stereotype.Service;
//
//@Service
//public class BroadcastingToUser {
//
//    private final SocketIOServer server;
//
//    public BroadcastingToUser(SocketIOServer server) {
//        this.server = server;
//    }
//
//    public void broadcastingMessageToUser(SendMessageBroadcasting dataToSend) {
//        server.getBroadcastOperations().sendEvent(dataToSend.getToUser(), dataToSend);
//    }
//
//    public void broadcastMessageToPublicChannel(SendMessageBroadcasting dataToSend) {
//        server.getRoomOperations("publicRoom").sendEvent("messageEvent", dataToSend);
//    }
//}
