package com.project.lunchuis.Controller;

import com.project.lunchuis.Model.Notification;
import com.project.lunchuis.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Crear una nueva notificación
    @PostMapping
    public ResponseEntity<Notification> createNotification(
            @RequestBody Notification notification,
            @RequestParam Long userId) {
        Notification newNotification = notificationService.createNotification(notification, userId);
        notificationService.sendNotification(newNotification);
        return ResponseEntity.ok(newNotification);
    }


    // Obtener todas las notificaciones
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    // Obtener una notificación por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Integer id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar una notificación existente
    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(
            @PathVariable Integer id,
            @RequestBody Notification updatedNotification
    ) {
        Optional<Notification> notification = notificationService.updateNotification(id, updatedNotification);
        return notification.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar una notificación por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Integer id) {
        if (notificationService.deleteNotification(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
