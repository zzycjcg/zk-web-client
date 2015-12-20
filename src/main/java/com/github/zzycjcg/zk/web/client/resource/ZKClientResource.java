package com.github.zzycjcg.zk.web.client.resource;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.github.zzycjcg.zk.web.client.exception.ZKOperateException;

/**
 * The Interface ZKClientResource.
 *
 * @author zhiyong zhu at 2015-12-20
 * @since v0.0.1
 */
@Path("zkclient")
public interface ZKClientResource
{
    
    /**
     * Gets the.
     *
     * @param path the path
     * @return the string
     * @throws ZKOperateException the ZK operate exception
     */
    @GET
    @Path("get")
    @Produces(MediaType.TEXT_PLAIN)
    String get(@QueryParam("path") String path)
        throws ZKOperateException;
        
    /**
     * Search.
     *
     * @param path the path
     * @param keyword the keyword
     * @return the list
     * @throws ZKOperateException the ZK operate exception
     */
    @POST
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    List<String> search(@FormParam("path") String path, @FormParam("keyword") String keyword)
        throws ZKOperateException;
        
    /**
     * Gets the children.
     *
     * @param path the path
     * @return the children
     * @throws ZKOperateException the ZK operate exception
     */
    @GET
    @Path("getChildren")
    @Produces(MediaType.APPLICATION_JSON)
    List<String> getChildren(@QueryParam("path") String path)
        throws ZKOperateException;
        
    /**
     * Adds the.
     *
     * @param path the path
     * @param value the value
     * @throws ZKOperateException the ZK operate exception
     */
    @POST
    @Path("add")
    @Produces(MediaType.TEXT_PLAIN)
    void add(@FormParam("path") String path, @FormParam("value") String value)
        throws ZKOperateException;
        
    /**
     * Delete.
     *
     * @param path the path
     * @throws ZKOperateException the ZK operate exception
     */
    @POST
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    void delete(@FormParam("path") String path)
        throws ZKOperateException;
        
    /**
     * Modify.
     *
     * @param path the path
     * @param value the value
     * @throws ZKOperateException the ZK operate exception
     */
    @POST
    @Path("modify")
    @Produces(MediaType.TEXT_PLAIN)
    void modify(@FormParam("path") String path, @FormParam("value") String value)
        throws ZKOperateException;
}
