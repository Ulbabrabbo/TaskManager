package org.prova.main;

import org.prova.data.Task;
import org.prova.data.Status;
import org.prova.service.TaskService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final TaskService myTaskService = new TaskService();

    public static void main(String[] args) {

        boolean running = true;

        // caricamento dati iniziali per prove
        myTaskService.createNewTask("prova", "questo è un task di prova", Status.TODO);
        myTaskService.createNewTask("seconda prova", "questo è un altro task di prova", Status.DONE);
        myTaskService.createNewTask("prova tris", "altra prova ", Status.IN_PROGRESS);

        // menù per selezione della funzione da svolgere
        while(running){

            System.out.println("Selezionare la funzione da eseguire:\n" +
                    "1: Creare un nuovo task\n" +
                    "2: Visualizzare tutti i task\n" +
                    "3: Cercare un task tramite ID\n" +
                    "4: Modificare un task esistente\n" +
                    "5: Eliminare un task\n" +
                    "6: Filtrare i task in base allo stato\n" +
                    "7: Uscire\n" );

            try {
                String menuSelection = input.nextLine();
                //input.nextLine();

                switch (menuSelection) {
                    case "1" -> createTask();
                    case "2" -> showAllTask();
                    case "3" -> searchTaskById();
                    case "4" -> modifyTask();
                    case "5" -> deleteTask();
                    case "6" -> filterTask();
                    case "7" -> {
                        running = false;
                        System.out.println("Esecuzione terminata");
                    }

                    default -> System.out.println("Scelta non valida\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserimento non valido.\n");
                // consumo l'input per non farlo andare avanti all'infinito
                input.nextLine();
            }
        }

//        myTaskService.showTaskList();
//        myTaskService.deleteTask(4);
//        myTaskService.showTaskList();

    }


    private static void createTask() {
        System.out.print("Inserire titolo: ");
        String titolo = input.nextLine();

        System.out.print("Inserire descrizione: ");
        String descrizione = input.nextLine();

        Status stato;
        System.out.print("Inserire stato progetto tra: \n" +
                "1): TODO\n" +
                "2): IN_PROGRESS\n" +
                "3): DONE\n");
        String statusSelection = input.nextLine();
        switch (statusSelection) {
            case "1":
                stato = Status.TODO;
                break;
            case "2":
                stato = Status.IN_PROGRESS;
                break;
            case "3":
                stato = Status.DONE;
                break;
            default:
                System.out.println("Scelta non valida! Impostato su TODO di default.\n");
                stato = Status.TODO;
                break;
        }

        myTaskService.createNewTask(titolo, descrizione, stato);
    }

    private static void showAllTask() {
        myTaskService.showTaskList();
    }

    private static void searchTaskById() {
        System.out.print("Inserire Id del task da ricercare: ");
        int idToSearch = input.nextInt();
        input.nextLine();

        myTaskService.searchTask(idToSearch);
    }

    private static void modifyTask() {
        System.out.print("Inserire Id del task da modificare: ");
        int idToSearch = input.nextInt();
        input.nextLine();

        myTaskService.searchTask(idToSearch);

        System.out.println("Selezionare il campo da modificare tra: \n" +
                "1: Titolo \n" +
                "2: Descrizione \n" +
                "3: Status");

        String choice = input.nextLine();

        switch(choice){
            case "1":
                System.out.print("Inserire nuovo titolo: ");
                String newTitle = input.nextLine();
                myTaskService.modifyTaskTitle(idToSearch, newTitle);
                break;

            case "2":
                System.out.print("Inserire nuova descrizione: ");
                String newDescription = input.nextLine();
                myTaskService.modifyTaskDescription(idToSearch, newDescription);
                break;

            case "3":
                System.out.print("Selezionare il nuova stato tra: \n" +
                        "1): TODO\n" +
                        "2): IN_PROGRESS\n" +
                        "3): DONE\n");

                Status status;
                String statusSelection = input.nextLine();
                switch (statusSelection) {
                    case "1":
                        status = Status.TODO;
                        break;
                    case "2":
                        status = Status.IN_PROGRESS;
                        break;
                    case "3":
                        status = Status.DONE;
                        break;
                    default:
                        System.out.println("Scelta non valida!\n");
//                        status = Status.TODO;
                        return;
                }

                myTaskService.modifyTaskStatus(idToSearch, status);
                break;

            default: System.out.println("Scelta non valida\n");
        }
    }

    private static void deleteTask() {
        System.out.print("Inserire Id del task da eliminare: ");
        int idToDelete = input.nextInt();
        input.nextLine();

        myTaskService.deleteTask(idToDelete);
    }

    private static void filterTask() {
        System.out.println("Selezionare lo status secondo cui filtrare tra: \n" +
                "1): TODO\n" +
                "2): IN_PROGRESS\n" +
                "3): DONE\n");

        Status status;
        String statusSelection = input.nextLine();
        switch (statusSelection) {
            case "1":
                status = Status.TODO;
                myTaskService.filterTask(status);
                break;
            case "2":
                status = Status.IN_PROGRESS;
                myTaskService.filterTask(status);
                break;
            case "3":
                status = Status.DONE;
                myTaskService.filterTask(status);
                break;
            default:
                System.out.println("Scelta non valida!\n");
                break;
        }

    }

}