package main.model;

import java.util.Optional;

public interface TaskService {
    Iterable<Task> findAll();
    Optional<Task> findById(int id);
    Task save(Task task);
    void delete(Task task);
    void deleteAll();
}
