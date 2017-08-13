package app.services;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CsrfFilter extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
      HttpServletRequest req = (HttpServletRequest) servletRequest;
      HttpServletResponse resp = (HttpServletResponse) servletResponse;

      req.setAttribute("csrf_token", "token1");

      // TODO: handle csrf checks
      boolean requiresCsrfCheck = false;
      boolean csrfTokenValid = false;

      if (!requiresCsrfCheck || csrfTokenValid) {
        filterChain.doFilter(servletRequest, servletResponse);
      } else {
        throw new ServletException("missing or invalid csrf token");
      }
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }
}
