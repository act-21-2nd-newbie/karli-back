package guide.springboot.sample;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }


/*    ModelAndView viewAll() {
        final var tasks = taskService.selectAll();
        final var modelAndView = new ModelAndView();
        modelAndView.setViewName("tasks/list");
        final var taskModels = tasks.stream().map(TaskController::toModel).collect(Collectors.toUnmodifiableList());
        modelAndView.addObject("tasks", taskModels);
        return modelAndView;
    }*/
    @GetMapping
    public List<Mytask> getTasks() {
        List<Mytask> result = taskService.getTasks();
        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MytaskIdentifier newTask(@RequestBody Mytask newTask) {
        //{ id: }{"id":"1","details":"hi","status":"done"}

        //save first!
        if(!StringUtils.hasLength(newTask.getStatus())) {
            newTask.setStatus("active");
        }
        
        taskService.save(newTask);


        //1. Return MAP
        // Map<String, String> result = new HashMap<>();
        // result.put("id", createdMytask.getId());
        //2. Return Object
        MytaskIdentifier tid = new MytaskIdentifier(newTask);
        return tid;
    }

    @PatchMapping(path="/{id}")
    public void updateTask(@PathVariable String id, @RequestBody Mytask updateTask) {
        Optional<Mytask> optionalMytask = taskService.findById(id);
        if (optionalMytask.isEmpty()) {
            return;
        }
        Mytask mytask = optionalMytask.get();

        boolean needUpdate = false;
        if (StringUtils.hasLength(updateTask.getDetails())) {
            mytask.setDetails(updateTask.getDetails());
            needUpdate = true;
        }
        if (StringUtils.hasLength(updateTask.getStatus())) {
            mytask.setStatus(updateTask.getStatus());
            needUpdate = true;
        }

        if(needUpdate) {
            taskService.save(mytask);
        }
    }
}
