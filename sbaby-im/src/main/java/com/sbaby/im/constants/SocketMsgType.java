package com.sbaby.im.constants;

public enum SocketMsgType {
	
	CHATTYPE ("聊天类型","CHATTYPE"),ROUGETYPE("通知推送类型","ROUGETYPE");
    private final String name;
    private final String type;
    private SocketMsgType(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
}
