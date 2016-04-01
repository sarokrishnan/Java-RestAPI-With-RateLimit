package com.saro.challenge.util;

/**
 * Created by sbalakrishnan on 4/1/16.
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Implements Filter class
public class RateLimitFilter implements Filter  {
    RateLimitUtil rateLimitUtil = new RateLimitUtil();
    HashMap<String,Long> suspendTrackMap = new HashMap();

    public void  init(FilterConfig config)
            throws ServletException{
    }

    public void  doFilter(ServletRequest request,
                          ServletResponse response,
                          FilterChain chain)
            throws java.io.IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if(  suspendTrackMap.get(httpRequest.getHeader("apikey").toString()) == null )   {

            if(rateLimitUtil.isRateLimited(httpRequest.getHeader("apikey"))){
                chain.doFilter(request,response);
            }else{

                suspendTrackMap.put(httpRequest.getHeader("apikey").toString(),System.currentTimeMillis());
                AccessDeniedException accessDeniedException = new AccessDeniedException("Please Try later, you have exceeded your api call limit per 10 seconds");
                try {
                    throw accessDeniedException;
                } catch (AccessDeniedException e) {
                    e.printStackTrace();
                }
            }

        } else{


           long diff = System.currentTimeMillis() - suspendTrackMap.get(httpRequest.getHeader("apikey").toString());

           if( diff > 60000){
               suspendTrackMap.remove(httpRequest.getHeader("apikey").toString());
               System.out.println("Resuming your service with apikey " + httpRequest.getHeader("apikey").toString());

           }else{
               System.out.println("You request with " + httpRequest.getHeader("apikey").toString() + " is suspended for another few minutes");
           }

        }

    }
    public void destroy(){
    }
}

