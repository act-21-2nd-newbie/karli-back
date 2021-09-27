package guide.springboot.sample;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<Mytask> getTasks() {
        return taskRepository.findAll();
    }


    public Mytask save(Mytask newTask) {
        return taskRepository.save(newTask);
    }

    public Optional<Mytask> findById(String id) {
        return taskRepository.findById(id);
    }

    public void delete(String id) {
        taskRepository.deleteById(id);
    }

    public void delete() {
        taskRepository.deleteAll();
    }
}
