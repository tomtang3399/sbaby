package com.sbaby.im.config.socket;

public class CacheMessageInfo {

	/**
	 * 缓存中存入的客户端编号（线上可用userId类似这种标示用户身份唯一性的字段代替）
	 */
	private String clientId;
	
	/**
	 * 客户端的sessionId
	 */
	private String sessionId;
	
	/**
	 * 是否在线的标识
	 */
	private Short connected;
	
	/**
	 * 最后一次连接的时间
	 */
	private Long connectedTimes;
	
	/**
	 * 最后一次断开连接的时间
	 */
	private Long disConnectedTimes;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Short getConnected() {
		return connected;
	}

	public void setConnected(Short connected) {
		this.connected = connected;
	}

	public Long getConnectedTimes() {
		return connectedTimes;
	}

	public void setConnectedTimes(Long connectedTimes) {
		this.connectedTimes = connectedTimes;
	}

	public Long getDisConnectedTimes() {
		return disConnectedTimes;
	}

	public void setDisConnectedTimes(Long disConnectedTimes) {
		this.disConnectedTimes = disConnectedTimes;
	}
	
}
