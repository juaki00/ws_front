package edu.fpdual.client.servlet;


import edu.fpdual.client.services.UserServices;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "nuevoUsuarioServletPre", urlPatterns ={"/nuevo_usuario-pre"})
public class nuevoUsuarioServletPre extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserServices userController = new UserServices();
        req.getSession().setAttribute("listaUsuarios", userController.getAllUsers());
        resp.sendRedirect("/JSP_y_JDBC/comun/usuarios.jsp");

    }
}