package com.duarte.taskmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int option;

        do {
            menu();

            option = convert(scanner.nextLine());

            switch (option) {
                case 1 -> create();
                case 2 -> list();
                case 3 -> search();
                case 4 -> complete();
                case 5 -> delete();
                case 6 -> System.out.println("\nHave a nice day!");
                default -> System.out.println("\nInvalid option. Please input an option between 1-6.");
            }
            System.out.println();
        } while (option != 6);

        scanner.close();
    }

    private static void menu() {
        System.out.println("***** Java Task Manager *****");
        System.out.println("1. Create Task");
        System.out.println("2. List Tasks");
        System.out.println("3. Search Task");
        System.out.println("4. Mark Task as Done");
        System.out.println("5. Delete Task");
        System.out.println("6. Exit");
        System.out.print("Select an option (1-6): ");
    }

    private static int convert(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static int askTaskIndex(String prompt) {
        System.out.print(prompt);
        int number = convert(scanner.nextLine());

        if (number < 1 || number > tasks.size()) {
            System.out.println("Invalid task number.");
            return -1;
        }
        return number - 1;
    }

    private static void create() {
        System.out.print("\nTask description: ");
        String description = scanner.nextLine().trim();

        if (description.isEmpty()) {
            System.out.println("Task cannot be empty.");
            return;
        }

        tasks.add(new Task(description));
        System.out.println("Task added!");
    }

    private static void list() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks yet.");
            return;
        }

        System.out.println("\n--- Your tasks ---");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void search() {
        System.out.print("\nSearch for: ");
        String term = scanner.nextLine().trim().toLowerCase();
        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(term)) {
                System.out.println((i + 1) + ". " + task);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks found.");
        }
    }

    private static void complete() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks to complete.");
            return;
        }

        list();
        int index = askTaskIndex("Number of the task to mark as done: ");
        if (index == -1) {
            return;
        }

        Task task = tasks.get(index);
        if (task.isDone()) {
            System.out.println("That task is already done.");
        } else {
            task.markDone();
            System.out.println("Task completed!");
        }
    }

    private static void delete() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks to delete.");
            return;
        }

        list();
        int index = askTaskIndex("Number of the task to delete: ");
        if (index == -1) {
            return;
        }

        tasks.remove(index);
        System.out.println("Task deleted!");
    }
}

