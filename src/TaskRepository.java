import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
  private String url = "jdbc:postgresql://localhost:5432/postgres";
  private String user = "postgres";
  private String password = "12345";

  public List<Task> getTasks() {
    List<Task> tasks = new ArrayList<>();
    String sql = "SELECT id, name, description, is_done, date_created FROM tasks";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         Statement statement = conn.createStatement();
         ResultSet res = statement.executeQuery(sql)) {
      while (res.next()) {
        int id = res.getInt("id");
        String name = res.getString("name");
        String description = res.getString("description");
        boolean isDone = res.getBoolean("is_done");
        LocalDate dateCreated = res.getDate("date_created").toLocalDate();

        Task task = new Task(name, description, dateCreated);
        task.setId(id);
        if (isDone) {
          task.markAsDone();
        }
        tasks.add(task);
      }
    } catch (Exception e) {
      System.out.println("Err" + e.getMessage());
    }

    return tasks;
  }

  public void saveTasks(Task task) {
    String sql = "INSERT INTO tasks (name, description, is_done, date_created) " +
        "VALUES (?, ?, ?, ?) RETURNING id";

    try (Connection connection = DriverManager.getConnection(url, user, password);
         PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, task.getName());
      statement.setString(2, task.getDescription());
      statement.setBoolean(3, task.isDone());
      statement.setDate(4, java.sql.Date.valueOf(task.getDate()));
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          task.setId(resultSet.getInt("id"));
          System.out.println("Task Saved");
        }
      }
    } catch (Exception e) {
      System.out.println("Err" + e.getMessage());
    }
  }

  public void deleteTask(Task task) {
    String sql = "DELETE FROM tasks WHERE id = ?";

    try (Connection connection = DriverManager.getConnection(url, user, password);
         PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setInt(1, task.getId());
      ps.executeUpdate();
      System.out.println("Task Deleted");
    } catch (Exception e) {
      System.out.println("Err" + e.getMessage());
    }
  }

  public void markDoneTask(Task task) {
    String sql = "UPDATE tasks SET is_done = ? WHERE id = ?";

    try (Connection connection = DriverManager.getConnection(url, user, password);
         PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setBoolean(1, true);
      ps.setInt(2, task.getId());
      ps.executeUpdate();
      task.markAsDone();
    } catch (Exception e) {
      System.out.println("Err" + e.getMessage());
    }
  }

  public Task getTask(int id) {
    String sql = "SELECT * FROM tasks WHERE id = ?";

    try (Connection connection = DriverManager.getConnection(url, user, password);
         PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setInt(1, id);
      try (ResultSet res = ps.executeQuery()) {
        if (res.next()) {
          Task task = new Task(res.getString("name"),
              res.getString("description"),
              res.getDate("date_created").toLocalDate());
          task.setId(id);
          return task;
        }
        return null;
      }

    } catch (Exception e) {
      System.out.println("Err" + e.getMessage());
    }
    return null;
  }
}