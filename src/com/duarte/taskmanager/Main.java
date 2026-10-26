package com.duarte.taskmanager;

import java.util.Scanner;

public class Main {

    private static final int MAX_TASKS = 50;
    private static final String[] list = new String[MAX_TASKS];
    private static int count = 0;
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
                case 4 -> delete();
                case 5 -> System.out.println("\nHave a nice day!");
                default -> System.out.println("\nInvalid option. Please input an option between 1-5.");
            }
            System.out.println();
        } while (option != 5);

        scanner.close();
    }

    private static void menu() {
        System.out.println("***** Java Task Manager *****");
        System.out.println("1. Create Task");
        System.out.println("2. List Task");
        System.out.println("3. Search Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.print("Select an option (1-5): ");
    }

    private static int convert(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void create() {
        if (count == MAX_TASKS) {
            System.out.println("\nTask list is full.");
            return;
        }

        System.out.print("\nTask description: ");
        String task = scanner.nextLine().trim();

        if (task.isEmpty()) {
            System.out.println("Task cannot be empty.");
            return;
        }

        list[count++] = task;
        System.out.println("Task added!");
    }

    private static void list() {
        if (count == 0) {
            System.out.println("\nNo tasks yet.");
            return;
        }

        System.out.println("\n--- Your tasks ---");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
    }

    private static void search() {
        System.out.print("\nSearch for: ");
        String term = scanner.nextLine().trim().toLowerCase();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (list[i].toLowerCase().contains(term)) {
                System.out.println((i + 1) + ". " + list[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks found.");
        }
    }

    private static void delete() {
        if (count == 0) {
            System.out.println("\nNo tasks to delete.");
            return;
        }

        list();
        System.out.print("Number of the task to delete: ");
        int number = convert(scanner.nextLine());

        if (number < 1 || number > count) {
            System.out.println("Invalid task number.");
            return;
        }

        for (int i = number - 1; i < count - 1; i++) {
            list[i] = list[i + 1];
        }
        list[--count] = null;

        System.out.println("Task deleted!");
    }
}


