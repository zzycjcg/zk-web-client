package com.github.zzycjcg.zk.web.client.resource.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.curator.framework.CuratorFramework;

import com.github.zzycjcg.zk.web.client.exception.ZKOperateException;
import com.github.zzycjcg.zk.web.client.resource.ZKClientResource;
import com.github.zzycjcg.zk.web.client.service.ZKServerManager;
import com.github.zzycjcg.zk.web.client.util.SessionHolder;

/**
 * The Class ZKClientResourceImpl.
 *
 * @author zhiyong zhu at 2015-12-19
 * @since v0.0.1
 */
public class ZKClientResourceImpl implements ZKClientResource
{
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
    
    /** {@inheritDoc} 
     * @throws ZKOperateException */
    
    @Override
    public List<String> search(String path, String keyword)
        throws ZKOperateException
    {
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(SessionHolder.getCurrentZKServer());
        List<String> children = null;
        try
        {
            children = curatorFramework.getChildren().forPath(path);
        }
        catch (Exception e)
        {
            throw new ZKOperateException("4", "failed to get children.", e);
        }
        List<String> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(children))
        {
            return Collections.emptyList();
        }
        for (String child : children)
        {
            if (child != null && keyword != null && child.toLowerCase().contains(keyword.toLowerCase()))
            {
                result.add(child);
            }
        }
        return result;
    }
    
    /** {@inheritDoc} 
     * @throws ZKOperateException */
    
    @Override
    public void add(String path, String value)
        throws ZKOperateException
    {
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(SessionHolder.getCurrentZKServer());
        try
        {
            curatorFramework.create().forPath(path, value == null ? null : value.getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            throw new ZKOperateException("4", "failed to CREATE data.", e);
        }
    }
    
    /** {@inheritDoc} 
     * @throws ZKOperateException */
    
    @Override
    public void delete(String path)
        throws ZKOperateException
    {
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(SessionHolder.getCurrentZKServer());
        try
        {
            curatorFramework.delete().forPath(path);
        }
        catch (Exception e)
        {
            throw new ZKOperateException("3", "failed to delete data.", e);
        }
    }
    
    /** {@inheritDoc} 
     * @throws ZKOperateException */
    
    @Override
    public void modify(String path, String value)
        throws ZKOperateException
    {
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(SessionHolder.getCurrentZKServer());
        try
        {
            curatorFramework.setData().forPath(path, value == null ? null : value.getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            throw new ZKOperateException("2", "failed to modify data.", e);
        }
    }
    
    /** {@inheritDoc} */
    
    @Override
    public String get(String path)
        throws ZKOperateException
    {
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(SessionHolder.getCurrentZKServer());
        try
        {
            byte[] data = curatorFramework.getData().forPath(path);
            return new String(data, "UTF-8");
        }
        catch (Exception e)
        {
            throw new ZKOperateException("1", "failed to get data.", e);
        }
    }
    
    /** {@inheritDoc} */
    
    @Override
    public List<String> getChildren(String path)
        throws ZKOperateException
    {
        CuratorFramework curatorFramework = zkServerManager.getCuratorFramework(SessionHolder.getCurrentZKServer());
        List<String> children = null;
        try
        {
            children = curatorFramework.getChildren().forPath(path);
        }
        catch (Exception e)
        {
            throw new ZKOperateException("4", "failed to get children.", e);
        }
        return children;
    }
    
}
