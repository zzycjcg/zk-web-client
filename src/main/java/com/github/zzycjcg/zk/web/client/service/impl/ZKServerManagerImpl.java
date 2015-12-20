package com.github.zzycjcg.zk.web.client.service.impl;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zzycjcg.zk.web.client.exception.ZKOperateErrorCode;
import com.github.zzycjcg.zk.web.client.exception.ZKOperateException;
import com.github.zzycjcg.zk.web.client.service.ZKServerManager;

/**
 * The Class ZKServerManagerImpl.
 *
 * @author zhiyong zhu at 2015-12-20
 * @since v0.0.1
 */
public class ZKServerManagerImpl implements ZKServerManager
{
    private static final Logger log = LoggerFactory.getLogger(ZKServerManagerImpl.class);
    
    private final Map<String, CuratorFramework> zkServerHolder = new ConcurrentHashMap<>();
    
    /** {@inheritDoc} 
     * @throws ZKOperateException */
    
    public void add(String serverAddress)
        throws ZKOperateException
    {
        CuratorFramework curatorFramework = zkServerHolder.get(serverAddress);
        if (curatorFramework != null)
        {
            // no need to add exist zk server.
            return;
        }
        synchronized (this)
        {
            // double check
            curatorFramework = zkServerHolder.get(serverAddress);
            if (curatorFramework != null)
            {
                // no need to add exist zk server.
                return;
            }
            
            curatorFramework = CuratorFrameworkFactory.newClient(serverAddress, getRetryPolicy());
            curatorFramework.start();
            boolean connected = false;
            try
            {
                connected = curatorFramework.blockUntilConnected(5, TimeUnit.SECONDS);
            }
            catch (InterruptedException e)
            {
                String errorMsg = "Wait zk connect failed. " + serverAddress;
                log.error(errorMsg, e);
            }
            if (!connected)
            {
                String errorMsg = "Connect to zk server failed: " + serverAddress;
                log.error(errorMsg);
                throw new ZKOperateException(ZKOperateErrorCode.E0001, errorMsg);
            }
            zkServerHolder.put(serverAddress, curatorFramework);
        }
        
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void delete(String serverAddress)
        throws ZKOperateException
    {
        zkServerHolder.remove(serverAddress);
    }
    
    /**
     * Gets the retry policy.
     *
     * @return the retry policy
     */
    protected RetryPolicy getRetryPolicy()
    {
        // 重试3次，重试间隔5s
        return new RetryNTimes(3, 5000);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public CuratorFramework getCuratorFramework(String serverAddress)
        throws ZKOperateException
    {
        CuratorFramework curatorFramework = zkServerHolder.get(serverAddress);
        if (curatorFramework == null)
        {
            String errorMsg = "ZK client is not available for this zk server: " + serverAddress;
            throw new ZKOperateException(ZKOperateErrorCode.E2001, errorMsg);
        }
        return curatorFramework;
    }
    
    /** {@inheritDoc} */
    
    @Override
    public Set<String> getAvailableZKServer()
        throws ZKOperateException
    {
        return zkServerHolder.keySet();
    }
}
