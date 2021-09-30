package guide.springboot.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Todo saveTodo(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<Todo> getTodoById(@PathVariable("id") Long id) {
        return todoService.getTodoById(id);
    }

    @PatchMapping("/{id}")
    public Optional<Todo> updateTodo(@PathVariable("id") Long id, @RequestBody Todo newTodo) {
        Optional<Todo> optionalOldTodo = todoService.getTodoById(id);
        Todo oldTodo = optionalOldTodo.get(); //update oldTodo to newTodo

        boolean needUpdate = false;
        if (StringUtils.hasLength(newTodo.getTask())) {
            oldTodo.setTask(newTodo.getTask());
            needUpdate = true;
        }
        if (newTodo.getStatus() != null) {
            oldTodo.setStatus(newTodo.getStatus());
            needUpdate = true;
        }

        if (needUpdate) {
            todoService.save(oldTodo);
        }

        return optionalOldTodo;
    }
    /*
    @PutMapping()
    public putTodo() {

    }*/
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
    }

    @DeleteMapping
    public void deleteTodos() {
        todoService.deleteAll();
    }
}
