package voot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DebugFilter extends GenericFilterBean {

  private static Logger LOG = LoggerFactory.getLogger(DebugFilter.class);

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    long now = System.currentTimeMillis();

    if (LOG.isDebugEnabled()) {
      LOG.debug("Request {} {} {} {}", now, request.getRemoteAddr(), request.getMethod(), request.getRequestURI());
    }

    chain.doFilter(req, res);

    if (LOG.isDebugEnabled()) {
      LOG.debug("Response {} {}", now, response.getStatus());
    }

  }
}
