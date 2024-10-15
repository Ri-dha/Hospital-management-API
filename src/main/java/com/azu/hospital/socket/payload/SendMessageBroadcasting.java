//package com.azu.hospital.socket.payload;
//
//import com.azu.hospital.utils.enums.socketEnums.BroadcastingBodyMessages;
//import com.azu.hospital.utils.enums.socketEnums.BroadcastingHeaderMessages;
//import com.azu.hospital.utils.enums.socketEnums.BroadcastingNavigateTo;
//import com.azu.hospital.utils.enums.socketEnums.BroadcastingStatus;
//import lombok.Data;
//
//@Data
//public class SendMessageBroadcasting<T> {
//
//    private String header;
//
//    private String body;
//
//    private String fromUser;
//
//    private String toUser;
//
//    private T data;
//
//    private BroadcastingStatus type;
//
//    private BroadcastingNavigateTo navigateTo;
//
//
//    public SendMessageBroadcasting() {
//    }
//
//    public SendMessageBroadcasting(String header, String body, String fromUser, String toUser, T data,
//                                   BroadcastingStatus type , BroadcastingNavigateTo navigateTo) {
//        this.header = header;
//        this.body = body;
//        this.fromUser = fromUser;
//        this.toUser = toUser;
//        this.data = data;
//        this.type = type;
//        this.navigateTo = navigateTo;
//    }
//}
