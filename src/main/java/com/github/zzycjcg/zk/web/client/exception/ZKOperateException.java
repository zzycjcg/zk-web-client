package com.github.zzycjcg.zk.web.client.exception;

public class ZKOperateException extends Exception
{
    private static final long serialVersionUID = 628459848479410553L;
    
    private final String code;
    
    private final String message;
    
    public ZKOperateException(String code, String message)
    {
        super(message);
        this.code = code;
        this.message = message;
    }
    
    public ZKOperateException(String code, String message, Throwable cause)
    {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
    
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
