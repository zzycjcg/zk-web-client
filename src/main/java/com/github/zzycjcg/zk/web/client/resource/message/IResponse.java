package com.github.zzycjcg.zk.web.client.resource.message;

/**
 * The Interface IResponse.
 *
 * @author zhiyong zhu at 2015-12-20
 * @since v0.0.1
 */
public interface IResponse
{
    
    /**
     * Gets the error code.
     *
     * @return the error code
     */
    String getErrorCode();
    
    /**
     * Gets the error message.
     *
     * @return the error message
     */
    String getErrorMessage();
}
