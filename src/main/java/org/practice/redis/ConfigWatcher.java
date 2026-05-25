package org.practice.redis;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ConfigWatcher {
    private static final String CONFIG_PATH = "/config/db_url";

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = ZKConnection.connect();

        // Read config + set watch
        byte[] data = zk.getData(CONFIG_PATH, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    // Re-read updated config when triggered
                    byte[] newData = zk.getData(CONFIG_PATH, this, null);
                    System.out.println("Config updated: " + new String(newData));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, null);

        System.out.println("Initial config: " + new String(data));

        Thread.sleep(Long.MAX_VALUE); // keep app alive
    }
}
