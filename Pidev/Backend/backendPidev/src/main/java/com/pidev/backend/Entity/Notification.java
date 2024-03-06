package com.pidev.backend.Entity;

public class Notification {
    private String message;
    // Ajoutez d'autres propriétés nécessaires

    public Notification() {
        // Constructeur par défaut nécessaire pour la désérialisation JSON
    }

    public Notification(String message) {
        this.message = message;
        // Initialisez d'autres propriétés si nécessaire
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
