package com.github.zzycjcg.zk.web.client.resource.impl;

import com.github.zzycjcg.zk.web.client.exception.ZKOperateException;
import com.github.zzycjcg.zk.web.client.resource.ZKServerManageResource;
import com.github.zzycjcg.zk.web.client.service.ZKServerManager;
import com.github.zzycjcg.zk.web.client.util.SessionHolder;

public class ZKServerManageResourceImpl implements ZKServerManageResource
{
    private ZKServerManager zkServerManager;
    
    /**
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
    
}
