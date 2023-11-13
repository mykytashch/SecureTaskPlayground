// Task.java in /Users/mykyta/Desktop/SecureTaskPlayground/code-with-quarkus/src/main/java/org/acme

package org.acme;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;

    // Конструктор по умолчанию
    public Task() {
    }

    // Конструктор с параметрами
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
