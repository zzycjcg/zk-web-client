package com.github.zzycjcg.zk.web.client.service;

import java.util.Set;

import org.apache.curator.framework.CuratorFramework;

import com.github.zzycjcg.zk.web.client.exception.ZKOperateException;

/**
 * The Interface ZKServerManager.
 *
 * @author zhiyong zhu at 2015-12-20
 * @since v0.0.1
 */
public interface ZKServerManager
{
    
    /**
     * Adds the.
     *
     * @param serverAddress the server address
     * @throws ZKOperateException the ZK operate exception
     */
    void add(String serverAddress)
        throws ZKOperateException;
        
    /**
     * Delete.
     *
     * @param serverAddress the server address
     * @throws ZKOperateException the ZK operate exception
     */
    void delete(String serverAddress)
        throws ZKOperateException;
        
    /**
     * Gets the curator framework.
     *
     * @param serverAddress the server address
     * @return the curator framework
     * @throws ZKOperateException the ZK operate exception
     */
    CuratorFramework getCuratorFramework(String serverAddress)
        throws ZKOperateException;
        
    /**
     * Gets the available zk server.
     *
     * @return the available zk server
     * @throws ZKOperateException the ZK operate exception
     */
    Set<String> getAvailableZKServer()
        throws ZKOperateException;
}
