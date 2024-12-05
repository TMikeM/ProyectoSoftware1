package com.project.lunchuis.Service;

import com.project.lunchuis.Model.Notification;
import com.project.lunchuis.Model.User;
import com.project.lunchuis.Repository.NotificationRepository;
import com.project.lunchuis.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender mailSender; // Inyección de JavaMailSender


    @Autowired
    private UserRepository userRepository; // Repositorio para UserModel


    // Crear una nueva notificación
    public Notification createNotification(Notification notification, Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }
    
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            notification.setUsuario(user.get());
            return notificationRepository.save(notification);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }


    // Obtener todas las notificaciones
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Obtener una notificación por su ID
    public Optional<Notification> getNotificationById(Integer id) {
        return notificationRepository.findById(id);
    }

    // Actualizar una notificación
    public Optional<Notification> updateNotification(Integer id, Notification updatedNotification) {
        return notificationRepository.findById(id).map(existingNotification -> {
            existingNotification.setDate(updatedNotification.getDate());
            existingNotification.setMessage(updatedNotification.getMessage());
            return notificationRepository.save(existingNotification);
        });
    }

    // Eliminar una notificación
    public boolean deleteNotification(Integer id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Lógica para enviar notificación
    public void sendNotification(Notification notification) {
        try {
            // Verificar que el usuario y su correo no sean nulos
            if (notification.getUsuario() == null || notification.getUsuario().getEmail() == null) {
                throw new IllegalArgumentException("El usuario o su correo no está definido");
            }

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("minewacoundgg@gmail.com");  // Asegúrate de usar la misma cuenta configurada
            mailMessage.setTo(notification.getUsuario().getEmail());
            mailMessage.setSubject("Nueva Notificación");
            mailMessage.setText(notification.getMessage());

            
            // Enviar el correo
            mailSender.send(mailMessage);
            System.out.println("Correo enviado a " + notification.getUsuario().getEmail());
        } catch (Exception e) {
            // Capturar cualquier error en el proceso de envío
            System.err.println("Error al enviar correo: " + e.getMessage());
        }
    }
    
    

}
