package org.prova.data;

public class Task {
    private int id;
    private String title;
    private String description;
    private Status status;
    private static int maxId = 1;

    public Task(String title, String description, Status status) {
        this.id = maxId++;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
