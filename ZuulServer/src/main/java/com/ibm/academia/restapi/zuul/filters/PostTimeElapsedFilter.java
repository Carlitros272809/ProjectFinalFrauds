package com.ibm.academia.restapi.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTimeElapsedFilter extends ZuulFilter {

	private final static Logger log = LoggerFactory.getLogger(PostTimeElapsedFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext context = RequestContext.getCurrentContext();
		
		HttpServletRequest request = context.getRequest();
		
		log.info("Post Filter starts");
		
		Long timeInit = (Long) request.getAttribute("timeInit");
		Long timeFinal = System.currentTimeMillis();
		
		Long timeElapsed = timeInit + timeFinal;
		
		log.info(String.format("Time elpased in seconds $s seg.", timeElapsed.doubleValue()/1000.00));
		log.info(String.format("Time elapsed in milliseconds %s ms", timeElapsed));
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
