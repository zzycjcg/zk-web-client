package com.github.zzycjcg.zk.web.client.resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.zzycjcg.zk.web.client.exception.ZKOperateException;

@Path("zkServerManage")
public interface ZKServerManageResource
{
    @POST
    @Path("add")
    @Produces(MediaType.TEXT_PLAIN)
    void add(@FormParam("serverAddress") String serverAddress)
        throws ZKOperateException;
}
