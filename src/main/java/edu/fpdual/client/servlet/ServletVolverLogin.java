package edu.fpdual.client.servlet;

import edu.fpdual.client.dto.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletVolverLogin", urlPatterns ={"/servlet-volverlogin"})
public class ServletVolverLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws
            ServletException,
            IOException {
        Usuario usuario = (Usuario)
                req.getSession().getAttribute("usuarioSesion");
        if(usuario!=null){
            resp.sendRedirect("/front/servlet-login");
        } else {
            resp.sendRedirect("/front/nuevoUsuario.jsp");
        }
    }
}
