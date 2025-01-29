package com.picpayteste.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpayteste.DTOs.NotificationDTO;
import com.picpayteste.Domain.User.User;

@Service
public class NotificationServices {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        // ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class );

        // if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
        //     System.out.println("Falha ao enviar notificação");
        //     throw new Exception("Falha ao enviar notificação");
        // }

        System.out.println("Notificação enviada com sucesso");
    }

}
