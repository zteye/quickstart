package com.zteye.quickstart.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ReplicatedMap;

public class ReplicatedMapSample2 {
	public static void main(String[] args) {
        // Start the Embedded Hazelcast Cluster Member.
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        // Get a Replicated Map called "my-replicated-map"
        ReplicatedMap<String, Long> map = hz.getReplicatedMap("my-replicated-map");
        // Put and Get a value from the Replicated Map
        // key/value replicated to all members
       // map.put("key", "value");
        // the value retrieved from local member
       // map.get("key");
        // Shutdown the Hazelcast Cluster Member
        
        boolean loop=true;
        while(loop) {
        	Long xxx = map.get("testkey");
        	if(xxx!=null) {
        		System.out.println("xxx="+xxx);
        		long now = System.currentTimeMillis();
        		System.out.println("cost time="+(now-xxx.longValue()));
        		loop=false;
        	}
        	try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
       // hz.shutdown();
    }
}
