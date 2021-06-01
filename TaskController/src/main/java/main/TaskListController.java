package main;

import main.model.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskListController {
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/tasks/")
    public List<Task> taskList() {
        Iterable<Task> taskIterable = taskService.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @PostMapping(value = "/tasks/")
    public int add(Task task) {
        Task newTask = taskService.save(task);
        return newTask.getId();
    }

    @DeleteMapping(value = "/tasks/")
    public void removeAll() {
        taskService.deleteAll();
    }

    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity remove(@PathVariable int id) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            taskService.delete(optionalTask.get());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity update(@PathVariable int id, Task task) {
        Optional<Task> optionalTask = taskService.findById(id);
        Task updatedTask;
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            task.setId(optionalTask.get().getId());
            updatedTask = taskService.save(task);
        }
        return new ResponseEntity(updatedTask, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }
}

