package com.github.zzycjcg.zk.web.client.exception;

/**
 * The Interface ZKOperateErrorCode.
 *
 * @author zhiyong zhu at 2015-12-20
 * @since v0.0.1
 */
public interface ZKOperateErrorCode
{
    /** ZK连接异常：无法连接到目标服务器. */
    String E0001 = "0001";
    
    /** ZK操作异常：无法获取节点数据. */
    String E1001 = "1001";
    
    /** ZK操作异常：无法获取子节点. */
    String E1002 = "1002";
    
    /** ZK操作异常：无法添加节点. */
    String E1003 = "1003";
    
    /** ZK操作异常：无法删除节点. */
    String E1004 = "1004";
    
    /** ZK操作异常：无法修改节点数据. */
    String E1005 = "1005";
    
    /** 操作异常：没有可用的zk客户端. */
    String E2001 = "2001";
}
