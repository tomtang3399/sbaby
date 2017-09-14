package com.sbaby.data.config.rpc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.AnnotationBean;

@Configuration
public class DubboConfig {
	
	@Value("${dubbo.provider.application.name}")  
    private String dubbo_provider_application_name;  
  
  
    @Value("${dubbo.registry.address}")
    private String dubbo_provider_registry_address;  
  
    /** 
     * 由《dubbo:application》转换过来 
     **/  
    @Bean("provider_application")
    public ApplicationConfig applicationProviderConfigs() {  
        ApplicationConfig applicationConfig = new ApplicationConfig();  
        applicationConfig.setLogger("slf4j");  
        applicationConfig.setName(dubbo_provider_application_name);  
        return applicationConfig;  
    }
    
    /** 
     * 与<dubbo:registry/>相当 
     * */  
    @Bean
    public RegistryConfig registryProviderConfigs() {  
        RegistryConfig registryConfig = new RegistryConfig();  
        registryConfig.setAddress(dubbo_provider_registry_address);
        registryConfig.setProtocol("zookeeper");
        return registryConfig;  
    }
    
    /**
     * 注册内部模块间服务调用模式，采用kryo序列化方式
     * @return
     */
    @Bean("dubbo")
	public ProtocolConfig protocolProviderConfigs() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName("dubbo");
		protocolConfig.setSerialization("kryo");//序列化方式采用kryo
		protocolConfig.setOptimizer("com.sbaby.common.core.DubboSerializationOptimizer");//序列化的类
		return protocolConfig;
	}
    
    /** 
    * 与<dubbo:annotation/>相当.提供方扫描带有@com.alibaba.dubbo.init.annotation.Service的注解类 
    * 
    */  
	 @Bean
	 public static AnnotationBean annotationProviderBean() {  
	     AnnotationBean annotationBean = new AnnotationBean();  
	     annotationBean.setPackage("com.sbaby.data.service.impl,com.sbaby.data.web");//多个包可使用英文逗号隔开
	     return annotationBean;
	 }
    
}
