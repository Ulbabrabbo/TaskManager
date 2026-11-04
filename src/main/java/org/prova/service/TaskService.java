package org.prova.service;

import org.prova.data.Task;
import org.prova.data.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TaskService {

    private List<Task> myTaskList = new ArrayList<>();

    // creazione di un nuovo task sfruttando costruttore della classe Task
    public void createNewTask(String title, String description, Status status){
        Task taskToAdd = new Task(title, description, status);
        myTaskList.add(taskToAdd);
        System.out.println("Nuovo task inserito!\n");
    }

    public void showTaskList(){
        // Controllo che la lista non sia vuota
        if (myTaskList.isEmpty()) {
            System.out.println("Non ci sono task nella lista.\n");
            return;
        }
        // stampo la lista sfruttando lo stream
        System.out.println("Lista di tutti i task: ");
        myTaskList.stream().forEach(task -> {
            System.out.println("id: " + task.getId() + "\n" +
            "titolo: " + task.getTitle() + "\n" +
            "descrizione: " + task.getDescription() + "\n" +
            "status: " + task.getStatus() + "\n");
        });
    }

    public void searchTask(int id) {
        try {
            Task taskToSearch = myTaskList.stream()
                    .filter(task -> task.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Task con id: " + id + " non trovato"));

            //System.out.println("ecco il task con id: " + id + "\n");

            System.out.println("id: " + taskToSearch.getId() + "\n" +
                    "titolo: " + taskToSearch.getTitle() + "\n" +
                    "descrizione: " + taskToSearch.getDescription() + "\n" +
                    "status: " + taskToSearch.getStatus() + "\n");

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyTaskTitle(int id, String newTitle) {
        try {
            Task taskToModify = myTaskList.stream()
                    .filter(task -> task.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Task con id: " + id + " non trovato"));

            taskToModify.setTitle(newTitle);
            System.out.println("Task con id: " + id + " modificato con titolo: " + newTitle + "\n");

        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    public void modifyTaskDescription(int id, String newDescription) {
        try {
            Task taskToModify = myTaskList.stream()
                    .filter(task -> task.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Task con id: " + id + " non trovato"));

            taskToModify.setDescription(newDescription);
            System.out.println("Task con id: " + id + " modificato con Descrizione: " + newDescription + "\n");

        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    public void modifyTaskStatus(int id, Status newStatus) {
        try {
            Task taskToModify = myTaskList.stream()
                    .filter(task -> task.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Task con id: " + id + " non trovato"));

            taskToModify.setStatus(newStatus);
            System.out.println("Task con id: " + id + " modificato con status: " + newStatus + "\n");

        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(int id){
        try {
            Task taskToDelete = myTaskList.stream()
                    .filter(task -> task.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Task con id: " + id + " non trovato"));

            myTaskList.remove(taskToDelete);
            System.out.println("cancellato task con id: " + id + "\n");

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public void filterTask(Status status) {

        if (myTaskList.isEmpty()) {
            System.out.println("Non ci sono task nella lista.\n");
            return;
        }

//        System.out.println("Lista di tutti i task con status: " + status);
//        myTaskList.stream().forEach(task -> {
//            if(task.getStatus() == status) {
//                System.out.println("id: " + task.getId() + "\n" +
//                        "titolo: " + task.getTitle() + "\n" +
//                        "descrizione: " + task.getDescription() + "\n" +
//                        "status: " + task.getStatus() + "\n");
//            }
//        });

        List<Task> filteredTaskList = myTaskList.stream()
                .filter(task -> task.getStatus() == status)
                .toList();
        if(filteredTaskList.isEmpty()) {
            System.out.println("La lista filtrata è vuota.\n");
        } else {
            System.out.println("Lista dei task filtrati: ");
            filteredTaskList.stream().forEach(task -> {
                System.out.println("id: " + task.getId() + "\n" +
                        "titolo: " + task.getTitle() + "\n" +
                        "descrizione: " + task.getDescription() + "\n" +
                        "status: " + task.getStatus() + "\n");
            });
        }

    }
}
