package com.github.zzycjcg.zk.web.client.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zzycjcg.zk.web.client.resource.message.IResponse;

/**
 * The Class DefaultExceptionMapper.
 *
 * @author zhiyong zhu at 2015-10-7
 * @since v0.0.1
 */
@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Throwable>
{
    
    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionMapper.class);
    
    /** {@inheritDoc} */
    
    @Override
    public Response toResponse(final Throwable e)
    {
        // web app exception return its origin response
        if (e instanceof WebApplicationException)
        {
            return ((WebApplicationException)e).getResponse();
        }
        
        if (e instanceof ZKOperateException)
        {
            return Response.ok(new IResponse()
            {
                @Override
                public String getErrorMessage()
                {
                    return ((ZKOperateException)e).getMessage();
                }
                
                @Override
                public String getErrorCode()
                {
                    return ((ZKOperateException)e).getErrorCode();
                }
            }, MediaType.APPLICATION_JSON_TYPE).build();
        }
        
        // other runtime exception return 500
        if (e instanceof RuntimeException)
        {
            log.error("Handle resource failed.", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        
        log.error("Handle resource failed.", e);
        // handle custom exception if defined.
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
    
}
