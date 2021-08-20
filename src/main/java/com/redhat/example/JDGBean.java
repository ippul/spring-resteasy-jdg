package com.redhat.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.api.BasicCacheContainer;

public class JDGBean {

    private RemoteCache<String, String> remoteCache;

    private BasicCacheContainer manager;

    @PostConstruct
    public void init() {
        remoteCache = (RemoteCache) getCacheContainer().getCache("simple");
    }

    private BasicCacheContainer getCacheContainer() {
        if (manager == null) {
            org.infinispan.client.hotrod.configuration.ConfigurationBuilder builder = new org.infinispan.client.hotrod.configuration.ConfigurationBuilder();
            builder.addServer()
            .host("datagrid-app-hotrod")
            .port(11333)
            .maxRetries(5)
            .socketTimeout(80000)
            .connectionTimeout(80000)
            ;
            manager = new RemoteCacheManager(builder.build());
        }
        return manager;
    }

    @PreDestroy
    public void cleanUp() {
        manager.stop();
        manager = null;
    }

    public void put(String key, String value) {
        this.remoteCache.put(key, value);
    }

    public String get(String key) {
        return this.remoteCache.get(key);
    }

}
