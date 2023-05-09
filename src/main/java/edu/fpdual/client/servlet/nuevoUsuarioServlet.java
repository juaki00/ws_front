package edu.fpdual.client.servlet;

import edu.fpdual.client.dto.Usuario;

import edu.fpdual.client.services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "nuevoUsuarioServlet", urlPatterns = {"/nuevo_usuario"})
public class nuevoUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws
            ServletException,
            IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws
            ServletException,
            IOException {
        Usuario usuario = (Usuario)
                req.getSession().getAttribute("usuarioSesion");
        try {

            String nickIntroducido = req.getParameter("nick");
            String passIntroducido = req.getParameter("passwd");
            String nombreIntroducido = req.getParameter("nombre");
            String apellidoIntroducido = req.getParameter("apellido");
            String telefonoIntroducido = req.getParameter("telefono");
            String emailIntroducido = req.getParameter("email");

            UserServices userServices = new UserServices();

            if (userServices.existeNick(nickIntroducido)) {
                req.getSession().setAttribute("existe", "El nick " + nickIntroducido + " ya existe");
                resp.sendRedirect("/front/comun/nuevoUsuario.jsp");
            } else {
                Usuario user = new Usuario(nickIntroducido, passIntroducido, nombreIntroducido, apellidoIntroducido, telefonoIntroducido, emailIntroducido);
                userServices.insertUser(user);
                System.out.println("Usuario insertado");
                userServices = new UserServices();
                req.getSession().setAttribute("listaUsuarios", userServices.getAllUsers());
                resp.sendRedirect("/front/comun/usuarios.jsp");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}