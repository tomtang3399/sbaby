package com.sbaby.job.config.rpc;

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
	
	@Value("${dubbo.application.name}")  
    private String dubbo_provider_application_name;  
  
  
    @Value("${zookeeper.cluster.address}")
    private String dubbo_provider_registry_address;  
  
    /** 
     * 由《dubbo:application》转换过来 
     **/  
    @Bean  
    public ApplicationConfig applicationConfigs() {  
        ApplicationConfig applicationConfig = new ApplicationConfig();  
        applicationConfig.setLogger("slf4j");  
        applicationConfig.setName(dubbo_provider_application_name);  
        return applicationConfig;  
    } 
    
    /** 
     * 与<dubbo:registry/>相当 
     * */  
    @Bean  
    public RegistryConfig registryConfigs() {  
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
	public ProtocolConfig protocolConfigs() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName("dubbo");
		protocolConfig.setSerialization("kryo");//序列化方式采用kryo
		protocolConfig.setOptimizer("com.sbaby.common.core.DubboSerializationOptimizer");//序列化的类
		return protocolConfig;
	}
    
    /** 
    * 与<dubbo:annotation/>相当.提供方扫描带有
    * @com.alibaba.dubbo.init.annotation.Service
    * @com.alibaba.dubbo.init.annotation.Reference
    * 的注解类 
    * 
    */  
	 @Bean  
	 public static AnnotationBean annotationBean() {  
	     AnnotationBean annotationBean = new AnnotationBean();  
	     annotationBean.setPackage("com.sbaby.job");//多个包可使用英文逗号隔开  
	     return annotationBean;  
	 }
    
}
