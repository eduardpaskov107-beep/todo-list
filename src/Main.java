import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static TaskManager taskManager = new TaskManager();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("TODO лист, вводи номер действия ниже");
    while (true) {
      System.out.println("1. Показать задачи");
      System.out.println("2. Добавить задачу");
      System.out.println("3. Отметить выполненной");
      System.out.println("4. Удалить задачу");
      System.out.println("5. Выход");
      String input = scanner.nextLine();

      switch (input.trim()) {
        case "1":
          writeTasks();
          break;

        case "2":
          addTask();
          break;

        case "3":
          markTask();
          break;

        case "4":
          deleteTask();
          break;

        case "5":
          return;
      }
    }
  }

  public static void writeTasks() {
    List<Task> tasks = taskManager.getTasks();
    for (Task task : tasks) {
      System.out.println(task.getId() + " | " + task);
    }
  }

  public static void addTask() {
    System.out.println("Введите имя задачи:");
    String name = scanner.nextLine();
    System.out.println("Введите описание задачи");
    String description = scanner.nextLine();
    Task task = new Task(name, description, LocalDate.now());
    taskManager.addTask(task);
  }

  public static void markTask() {
    System.out.println("Введите номер задачи:");
    int index = Integer.parseInt(scanner.nextLine());
    taskManager.doneTask(index);
  }

  public static void deleteTask() {
    System.out.println("Введите номер задачи:");
    int index = Integer.parseInt(scanner.nextLine());
    taskManager.deleteTask(index);
  }
}
