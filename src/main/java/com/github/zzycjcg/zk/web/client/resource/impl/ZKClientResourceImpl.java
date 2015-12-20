package com.github.zzycjcg.zk.web.client.resource.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zzycjcg.zk.web.client.exception.ZKOperateErrorCode;
import com.github.zzycjcg.zk.web.client.exception.ZKOperateException;
import com.github.zzycjcg.zk.web.client.resource.ZKClientResource;
import com.github.zzycjcg.zk.web.client.service.ZKServerManager;
import com.github.zzycjcg.zk.web.client.service.impl.ZKServerManagerImpl;
import com.github.zzycjcg.zk.web.client.util.SessionHolder;

/**
 * The Class ZKClientResourceImpl.
 *
 * @author zhiyong zhu at 2015-12-19
 * @since v0.0.1
 */
public class ZKClientResourceImpl implements ZKClientResource
{
    private static final Logger log = LoggerFactory.getLogger(ZKServerManagerImpl.class);
    
    private ZKServerManager zkServerManager;
    
    /**
     * Sets the zk server manager.
     *
     * @param zkServerManager the zkServerManager to set
     */
    public void setZkServerManager(ZKServerManager zkServerManager)
    {
        this.zkServerManager = zkServerManager;
    }
    
    /** {@inheritDoc} */
    
    @Override
    public List<String> search(String path, String keyword)
        throws ZKOperateException
    {
        String zkServer = SessionHolder.getCurrentZKServer();
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(zkServer);
        List<String> children = null;
        try
        {
            children = curatorFramework.getChildren().forPath(path);
        }
        catch (Exception e)
        {
            String errorMsg = "Failed to get children, the zk server is " + zkServer + ", the path is " + path;
            log.error(errorMsg, e);
            throw new ZKOperateException(ZKOperateErrorCode.E1002, errorMsg, e);
        }
        if (CollectionUtils.isEmpty(children))
        {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        for (String child : children)
        {
            if (child != null && keyword != null && child.toLowerCase().contains(keyword.toLowerCase()))
            {
                result.add(child);
            }
        }
        return result;
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void add(String path, String value)
        throws ZKOperateException
    {
        String zkServer = SessionHolder.getCurrentZKServer();
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(zkServer);
        try
        {
            curatorFramework.create().forPath(path, value == null ? null : value.getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            String errorMsg = "Failed to create node, the zk server is " + zkServer + ", the path is " + path;
            log.error(errorMsg, e);
            throw new ZKOperateException(ZKOperateErrorCode.E1003, errorMsg, e);
        }
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void delete(String path)
        throws ZKOperateException
    {
        String zkServer = SessionHolder.getCurrentZKServer();
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(zkServer);
        try
        {
            curatorFramework.delete().forPath(path);
        }
        catch (Exception e)
        {
            String errorMsg = "Failed to delete node, the zk server is " + zkServer + ", the path is " + path;
            log.error(errorMsg, e);
            throw new ZKOperateException(ZKOperateErrorCode.E1004, errorMsg, e);
        }
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void modify(String path, String value)
        throws ZKOperateException
    {
        String zkServer = SessionHolder.getCurrentZKServer();
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(zkServer);
        try
        {
            curatorFramework.setData().forPath(path, value == null ? null : value.getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            String errorMsg = "Failed to set data, the zk server is " + zkServer + ", the path is " + path;
            log.error(errorMsg, e);
            throw new ZKOperateException(ZKOperateErrorCode.E1005, errorMsg, e);
        }
    }
    
    /** {@inheritDoc} */
    
    @Override
    public String get(String path)
        throws ZKOperateException
    {
        String zkServer = SessionHolder.getCurrentZKServer();
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(zkServer);
        try
        {
            byte[] data = curatorFramework.getData().forPath(path);
            return new String(data, "UTF-8");
        }
        catch (Exception e)
        {
            String errorMsg = "Failed to get data, the zk server is " + zkServer + ", the path is " + path;
            log.error(errorMsg, e);
            throw new ZKOperateException(ZKOperateErrorCode.E1001, errorMsg, e);
        }
    }
    
    /** {@inheritDoc} */
    
    @Override
    public List<String> getChildren(String path)
        throws ZKOperateException
    {
        String zkServer = SessionHolder.getCurrentZKServer();
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(zkServer);
        List<String> children = null;
        try
        {
            children = curatorFramework.getChildren().forPath(path);
        }
        catch (Exception e)
        {
            String errorMsg = "Failed to get children, the zk server is " + zkServer + ", the path is " + path;
            log.error(errorMsg, e);
            throw new ZKOperateException(ZKOperateErrorCode.E1002, errorMsg, e);
        }
        return children;
    }
    
}
