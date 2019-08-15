package com.zteye.quickstart.hazelcast;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;
import com.hazelcast.cache.ICache;
import com.zteye.quickstart.hazelcast.pojo.User;

public class JCacheSample3 {
	public static void main(String[] args) {
        // Run as a Hazelcast Member
        System.setProperty("hazelcast.jcache.provider.type", "server");
        // Create the JCache CacheManager
        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<String, User> configuration = new MutableConfiguration<String, User>();
        // Expire entries after 1 minute
        configuration.setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ETERNAL));
        // Get a Cache called "myCache" and configure with 1 minute expiry
        Cache<String, User> myCache = manager.createCache("myCache", configuration);
        // Put and Get a value from "myCache"
        User user = new User();
        user.setUserName("name");
        user.setPassword("pwd");
        myCache.put("key", user);
        User value = myCache.get("key");
        System.out.println(value);
        
        //Shutdown the underlying Hazelcast Cluster Member
       // manager.getCachingProvider().close();
    }
}
