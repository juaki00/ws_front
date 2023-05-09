package edu.fpdual.client.services;

import edu.fpdual.client.dto.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.util.Set;

public class UserServices {

    private final WebTarget webTarget;

    /**
     * Servicios para acceder a la tabla de datos usuaios
     */
    public UserServices() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8080/ws/webapi");
    }

    /**
     *
     * @return Devuelve todos los usuarios registrados
     */
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<Usuario> getAllUsers() {
        GenericType<Set<Usuario>> type = new GenericType<Set<Usuario>>() {};

        return webTarget.path("users/getAll")
                .request(MediaType.APPLICATION_JSON)
                .get(type);
    }

    public boolean insertUser(Usuario user) {
        return webTarget.path("users/insert/")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON), Boolean.class);
    }

    /**
     *
     * @param user nick del usuario
     * @return devuelve la contraseña del usuario
     */
    public String getPass(String user) {
        return webTarget.path("users/getPass/")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON), String.class);
    }

    public Usuario userFromNick(String nick) {
        return webTarget.path("users/userFromNick/")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(nick, MediaType.APPLICATION_JSON), Usuario.class);
    }


    public Boolean existeNick(String nick) {

        return webTarget.path("users/existe/" + nick)
                .request(MediaType.APPLICATION_JSON)
                .get(Boolean.class);
    }

}
