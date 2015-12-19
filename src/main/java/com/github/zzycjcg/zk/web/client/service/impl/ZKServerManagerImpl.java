package com.github.zzycjcg.zk.web.client.service.impl;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zzycjcg.zk.web.client.exception.ZKOperateException;
import com.github.zzycjcg.zk.web.client.service.ZKServerManager;

public class ZKServerManagerImpl implements ZKServerManager
{
    private static final Logger log = LoggerFactory.getLogger(ZKServerManagerImpl.class);
    
    private final Map<String, CuratorFramework> zkServerHolder = new ConcurrentHashMap<>();
    
    /** {@inheritDoc} 
     * @throws ZKOperateException */
    
    public void add(String serverAddress)
        throws ZKOperateException
    {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(serverAddress, getRetryPolicy());
        curatorFramework.start();
        boolean connected = false;
        try
        {
            connected = curatorFramework.blockUntilConnected(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            log.error("Wait zk connect failed.", e);
        }
        if (!connected)
        {
            String errorMsg = "Connect to zk server failed: " + serverAddress;
            log.error(errorMsg);
            throw new ZKOperateException("0", errorMsg);
        }
        zkServerHolder.put(serverAddress, curatorFramework);
    }
    
    /** {@inheritDoc} */
    
    public void delete(String serverAddress)
        throws ZKOperateException
    {
    }
    
    protected RetryPolicy getRetryPolicy()
    {
        // 重试3次，重试间隔5s
        return new RetryNTimes(3, 5000);
    }
    
    @Override
    public CuratorFramework getCuratorFramework(String serverAddress)
        throws ZKOperateException
    {
        CuratorFramework curatorFramework = zkServerHolder.get(serverAddress);
        if (curatorFramework == null)
        {
            String errorMsg = "ZK server address is invalid: " + serverAddress;
            throw new ZKOperateException("0", errorMsg);
        }
        return curatorFramework;
    }
}
