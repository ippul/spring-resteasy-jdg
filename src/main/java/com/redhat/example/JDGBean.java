package com.redhat.example;

import javax.annotation.PostConstruct;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

public class JDGBean {

    private static final String APP_NAME = "datagrid-service";
    
    private static final String SVC_DNS_NAME = "datagrid-service";
    
    private static final String USER = "rhdgAdmin";
    
    private static final String PASSWORD = "Pa$$w0rd";
    
    private RemoteCache<String, String> remoteCache;

    @PostConstruct
    public void init(){
        ConfigurationBuilder cfg =
        JDGConfigurationUtils.create(SVC_DNS_NAME, APP_NAME, USER, PASSWORD);
        final RemoteCacheManager remote = new RemoteCacheManager(cfg.build());
        this.remoteCache = remote.getCache();
    }

    public  void put(String key, String value) {
        this.remoteCache.put(key, value);
     }
     
     public  String  get(String key) {
        return this.remoteCache.get(key);
     }

}
