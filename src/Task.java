import java.time.LocalDate;

public class Task {
  private String name;
  private boolean isDone;
  private String description;
  private LocalDate date;
  private int id;

  public Task(String name, String description, LocalDate date) {
    this.name = name;
    this.isDone = false;
    this.description = description;
    this.date = date;
  }

  public Task(String name, String description, LocalDate date, Boolean isDone) {
    this.name = name;
    this.isDone = isDone;
    this.description = description;
    this.date = date;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public boolean isDone() {
    return isDone;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDate() {
    return date;
  }

  public void markAsDone() {
    isDone = true;
  }

  @Override
  public String toString() {
    String mark = isDone ? "Done" : "Not done";
    return "[" + mark + "] " + name + ": " + description;
  }
}
