package com.github.zzycjcg.zk.web.client.exception;

/**
 * The Class ZKOperateException.
 *
 * @author zhiyong zhu at 2015-12-20
 * @since v0.0.1
 */
public class ZKOperateException extends Exception
{
    private static final long serialVersionUID = 628459848479410553L;
    
    private final String code;
    
    private final String message;
    
    /**
     * Instantiates a new ZK operate exception.
     *
     * @param code the code
     * @param message the message
     */
    public ZKOperateException(String code, String message)
    {
        super(message);
        this.code = code;
        this.message = message;
    }
    
    /**
     * Instantiates a new ZK operate exception.
     *
     * @param code the code
     * @param message the message
     * @param cause the cause
     */
    public ZKOperateException(String code, String message, Throwable cause)
    {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
    
    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getErrorCode()
    {
        return code;
    }
    
    /** {@inheritDoc} */
    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ZKOperateException [code=");
        builder.append(code);
        builder.append(", message=");
        builder.append(message);
        builder.append("]");
        return builder.toString();
    }
}
