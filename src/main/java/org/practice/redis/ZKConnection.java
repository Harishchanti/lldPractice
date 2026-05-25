package org.practice.redis;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher;

public class ZKConnection {

    public static ZooKeeper connect() throws Exception {
        return new ZooKeeper(
                "localhost:2181",
                3000,
                event -> System.out.println("Event received: " + event)
        );
    }
}
