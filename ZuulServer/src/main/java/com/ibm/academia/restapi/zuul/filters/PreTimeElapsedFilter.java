package com.ibm.academia.restapi.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTimeElapsedFilter extends ZuulFilter {

	private final static Logger log = LoggerFactory.getLogger(PreTimeElapsedFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext context = RequestContext.getCurrentContext();
		
		HttpServletRequest request = context.getRequest();
		
		log.info(String.format("%s enrutado a %s",request.getMethod(), request.getRequestURL().toString()));
		
		Long timeInit = System.currentTimeMillis();
		request.setAttribute("timeInit", timeInit);
		
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
