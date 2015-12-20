package com.github.zzycjcg.zk.web.client.resource.impl;

import java.util.Set;

import com.github.zzycjcg.zk.web.client.exception.ZKOperateException;
import com.github.zzycjcg.zk.web.client.resource.ZKServerManageResource;
import com.github.zzycjcg.zk.web.client.service.ZKServerManager;
import com.github.zzycjcg.zk.web.client.util.SessionHolder;

/**
 * The Class ZKServerManageResourceImpl.
 *
 * @author zhiyong zhu at 2015-12-20
 * @since v0.0.1
 */
public class ZKServerManageResourceImpl implements ZKServerManageResource
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
    
    /** {@inheritDoc} */
    
    @Override
    public void add(String serverAddress)
        throws ZKOperateException
    {
        zkServerManager.add(serverAddress);
        SessionHolder.setCurrentZKServer(serverAddress);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public Set<String> getAvailableZKServer()
        throws ZKOperateException
    {
        return zkServerManager.getAvailableZKServer();
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void selectCurrent(String serverAddress)
        throws ZKOperateException
    {
        // check serverAddress valid.
        zkServerManager.getCuratorFramework(serverAddress);
        SessionHolder.setCurrentZKServer(serverAddress);
    }
    
}
