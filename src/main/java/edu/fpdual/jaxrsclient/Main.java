package edu.fpdual.jaxrsclient;

import edu.fpdual.client.dto.Usuario;
import edu.fpdual.client.services.UserServices;
import edu.fpdual.jaxrsclient.client.NotificationClient;
import edu.fpdual.jaxrsclient.client.dto.Notification;

public class Main {


    public static void main(String[] args) {
//        System.out.println(new NotificationClient().ping());
        System.out.println(new UserServices().insertUser(new Usuario("bbbbbb","bb","bb","bb","bb","bb")));
        System.out.println(new UserServices().getPass("w"));
        System.out.println(new UserServices().getAllUsers());
        System.out.println(new UserServices().userFromNick("bbbbbb"));

        System.out.println(new NotificationClient().getNotification("8"));
        System.out.println(new NotificationClient().getNotification("82", ":D"));
        System.out.println(new NotificationClient().putNotification("82", ":D"));
        System.out.println(new NotificationClient().postNotification(
                Notification.builder().id(9).body("Prueba de consumo de servicio").title("Prueba").build()));
    }

}
