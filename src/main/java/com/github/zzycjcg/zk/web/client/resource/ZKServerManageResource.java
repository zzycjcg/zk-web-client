package com.github.zzycjcg.zk.web.client.resource;

import java.util.Set;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.zzycjcg.zk.web.client.exception.ZKOperateException;

/**
 * The Interface ZKServerManageResource.
 *
 * @author zhiyong zhu at 2015-12-20
 * @since v0.0.1
 */
@Path("zkServerManage")
public interface ZKServerManageResource
{
    /**
     * Adds the.
     *
     * @param serverAddress the server address
     * @throws ZKOperateException the ZK operate exception
     */
    @POST
    @Path("add")
    @Produces(MediaType.TEXT_PLAIN)
    void add(@FormParam("serverAddress") String serverAddress)
        throws ZKOperateException;
        
    /**
     * Gets the available zk server.
     *
     * @return the available zk server
     * @throws ZKOperateException the ZK operate exception
     */
    @GET
    @Path("getAvailableZKServer")
    @Produces(MediaType.APPLICATION_JSON)
    Set<String> getAvailableZKServer()
        throws ZKOperateException;
        
    /**
     * Select current.
     *
     * @param serverAddress the server address
     * @throws ZKOperateException the ZK operate exception
     */
    @POST
    @Path("selectCurrent")
    @Produces(MediaType.TEXT_PLAIN)
    void selectCurrent(@FormParam("serverAddress") String serverAddress)
        throws ZKOperateException;
}
