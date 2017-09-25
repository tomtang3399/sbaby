package com.sbaby.im.config.socket;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;

public class AuthListener implements AuthorizationListener{

	@Override
	public boolean isAuthorized(HandshakeData arg0) {
		// TODO Auto-generated method stub
		
		/**
		 * 获取认证token
		 */
		String token = arg0.getSingleUrlParam("token");
		
		/**
		 * token鉴权,通过则返回true
		 */
		return true;
	}

}