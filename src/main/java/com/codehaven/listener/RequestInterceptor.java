package com.codehaven.listener;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.mgmt.MutableTilesContainer;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.servlet.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by coder on 1/24/16.
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private String orgId = "partex";

    @Autowired
    private TilesConfigurer tilesConfigurer;
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("RequestInterceptor: REQUEST Intercepted for URI: " + request.getRequestURI());
        request.setAttribute("special", "I Love Animals!");

//        System.out.println("----------- tilesConfig:: " + tilesConfigurer.setDefinitions(""));

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
 
    }
}
