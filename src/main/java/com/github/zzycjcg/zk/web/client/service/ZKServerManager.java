package com.github.zzycjcg.zk.web.client.service;

import org.apache.curator.framework.CuratorFramework;

import com.github.zzycjcg.zk.web.client.exception.ZKOperateException;

public interface ZKServerManager
{
    void add(String serverAddress)
        throws ZKOperateException;
        
    void delete(String serverAddress)
        throws ZKOperateException;
        
    CuratorFramework getCuratorFramework(String serverAddress)
        throws ZKOperateException;
}
