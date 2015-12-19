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

@Path("zkclient")
public interface ZKClientResource
{
    @GET
    @Path("get")
    @Produces(MediaType.TEXT_PLAIN)
    String get(@QueryParam("path") String path)
        throws ZKOperateException;
        
    @POST
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    List<String> search(@FormParam("path") String path, @FormParam("keyword") String keyword)
        throws ZKOperateException;
        
    @GET
    @Path("getChildren")
    @Produces(MediaType.APPLICATION_JSON)
    List<String> getChildren(@QueryParam("path") String path)
        throws ZKOperateException;
        
    @POST
    @Path("add")
    @Produces(MediaType.TEXT_PLAIN)
    void add(@FormParam("path") String path, @FormParam("value") String value)
        throws ZKOperateException;
        
    @POST
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    void delete(@FormParam("path") String path)
        throws ZKOperateException;
        
    @POST
    @Path("modify")
    @Produces(MediaType.TEXT_PLAIN)
    void modify(@FormParam("path") String path, @FormParam("value") String value)
        throws ZKOperateException;
}
