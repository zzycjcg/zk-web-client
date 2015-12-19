package com.github.zzycjcg.zk.web.client.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.github.zzycjcg.zk.web.client.util.SessionHolder;

/**
 * The Class ResourceSessionCheckFilter.
 *
 * @author zhiyong zhu at 2015-10-7
 * @since v0.0.1
 */
public class SessionKeeperFilter implements Filter
{
    
    /** {@inheritDoc} */
    
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException
    {
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        SessionHolder.setRequest((HttpServletRequest)request);
        SessionHolder.setSession(((HttpServletRequest)request).getSession());
        // MUST why?
        chain.doFilter(request, response);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void destroy()
    {
    }
    
}
