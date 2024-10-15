//package com.azu.hospital.socket.controller;
//
//
//import com.azu.hospital.socket.payload.SocketMessage;
//import com.corundumstudio.socketio.AckRequest;
//import com.corundumstudio.socketio.SocketIOClient;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.listener.ConnectListener;
//import com.corundumstudio.socketio.listener.DataListener;
//import com.corundumstudio.socketio.listener.DisconnectListener;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//@Component
//@Log4j2
//public class SocketIOController {
//
//
//
//    private final SocketIOServer socketIOServer;
//
//    @Autowired
//    SocketIOController(SocketIOServer socketIOServer){
//        this.socketIOServer = socketIOServer;
//        this.socketIOServer.addConnectListener(onUserConnectWithSocket);
//        this.socketIOServer.addDisconnectListener(onUserDisconnectWithSocket);
//        this.socketIOServer.addEventListener("messageSendToUser", SocketMessage.class , onSendMessage);
//    }
//
//
//    public ConnectListener onUserConnectWithSocket = new ConnectListener() {
//        @Override
//        public void onConnect(SocketIOClient socketIOClient) {
//            log.info("Perform operation on user connect in controller");
//        }
//
//    };
//
//
//    public DisconnectListener onUserDisconnectWithSocket = new DisconnectListener() {
//        @Override
//        public void onDisconnect(SocketIOClient client) {
//            log.info("Perform operation on user disconnect in controller");
//        }
//    };
//
//
//
//
//    public DataListener<SocketMessage> onSendMessage = new DataListener<SocketMessage>() {
//
//        @Override
//        public void onData(SocketIOClient socketIOClient, SocketMessage message, AckRequest ackRequest) throws Exception {
//            log.info(socketIOClient);
//            socketIOServer.getBroadcastOperations().sendEvent(message.getTargetUserName(), socketIOClient , message);
//
//            ackRequest.sendAckData("success");
//        }
//    };
//
//
//}
