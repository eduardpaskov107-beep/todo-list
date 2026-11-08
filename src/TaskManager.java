import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private TaskRepository taskRepository;
    public TaskManager() {
        taskRepository = new TaskRepository();
    }

    public List<Task> getTasks() {
        return taskRepository.getTasks();
    }

    public void addTask(Task task) {
        taskRepository.saveTasks(task);
    }

    public void deleteTask(int id) {
        taskRepository.deleteTask(taskRepository.getTask(id));
    }

    public void doneTask(int id) {
        taskRepository.markDoneTask(taskRepository.getTask(id));
    }
}
