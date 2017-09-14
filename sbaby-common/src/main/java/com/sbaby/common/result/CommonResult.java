package com.sbaby.common.result;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CommonResult extends ResultParams {

	/**
	 * 回调Url.
	 */
	protected String callbackUrl;

	/**
	 * 返回Code.
	 */
	protected String resultCode;

	/**
	 * 返回信息.
	 */
	protected String resultMessage;

	/**
	 * task的code,通过taskCode查询具体信息.
	 */
	protected String taskCode;
	
	public CommonResult() {
		this.resultCode = SUCCESS_RESULT_CODE;
		this.resultMessage = SUCCESS_RESULT_MESSAGE;
	}
	
	/**
	 * 创建结果.
	 */
	public void setError(String errorCode, String errorMessage, String taskCode) {
		this.resultCode = errorCode;
		this.resultMessage = errorMessage;
		this.taskCode = taskCode;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	
	public static boolean isSuccessful(CommonResult result) {
		return SUCCESS_RESULT_CODE.equals(result.getResultCode());
	}
	
	/**
	 * 重新实现toString()函数方便在日志中打印Entity信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
