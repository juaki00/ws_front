package edu.fpdual.client.servlet;

import edu.fpdual.client.services.UserServices;
import edu.fpdual.client.dto.Usuario;
//import manager.impl.UserManagerImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;

@WebServlet(name = "ServletLogin", urlPatterns = {"/servlet-login"})
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws
            ServletException,
            IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Usuario usuario = null;
            UserServices userService = new UserServices();
            String nickIntroducido = req.getParameter("usuario");
            String passwordIntroducido = req.getParameter("contrasena");
            String passwodCorrecto = null;

            passwodCorrecto = userService.getPass(nickIntroducido);

            usuario = userService.userFromNick(nickIntroducido);

            if ((usuario != null && passwordIntroducido != null && passwordIntroducido.equals(passwodCorrecto))) {
                req.getSession().setMaxInactiveInterval(40);
                req.getSession().setAttribute("listaUsuarios", userService.getAllUsers());
                req.getSession().setAttribute("usuarioSesion", usuario);
                resp.sendRedirect("/front/comun/usuarios.jsp");
            } else {
                req.setAttribute("error", "Error al insertar usuario o contraseña");

                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }catch (Exception e){
            req.setAttribute("error", "Error al conectarse a la base de datos");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }
}
