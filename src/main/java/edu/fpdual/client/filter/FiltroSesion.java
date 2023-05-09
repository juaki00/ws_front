package edu.fpdual.client.filter;

import edu.fpdual.client.dto.Usuario;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="FiltroSesion", urlPatterns={"/comun/*"}, dispatcherTypes= {DispatcherType.REQUEST,DispatcherType.FORWARD})
public class FiltroSesion implements  Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)servletRequest;

        Usuario usuario = (Usuario)req.getSession().getAttribute("usuarioSesion");

        if(usuario == null){
            ((HttpServletResponse)servletResponse).sendRedirect("/front/login.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}