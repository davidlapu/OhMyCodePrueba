package com.ohmycode.todolist.controllers;

import com.ohmycode.todolist.models.dto.todo.TodoDto;
import com.ohmycode.todolist.models.entities.Todo;
import com.ohmycode.todolist.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final int PAGE_SIZE = 10;

    @GetMapping("/todo")
    public List<Todo> getTodos(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "title") String sortingParam,
                               @RequestParam(defaultValue = "asc") String sortingDirection,
                               @RequestParam Optional<String> title,
                               @RequestParam Optional<String> username) {

        return todoService.findAll(page, PAGE_SIZE, Direction.fromString(sortingDirection), sortingParam, title, username);
    }

    @PostMapping("/todo")
    public void insertTodo(@RequestBody TodoDto todoDto) {
        todoService.insert(todoDto);
    }

    @PutMapping("/todo")
    public void updateTodo(@RequestBody TodoDto todoDto) {
        todoService.update(todoDto);
    }

    @DeleteMapping("/todo")
    public void deleteTodo(@RequestParam Long id) {
        todoService.deleteById(id);
    }
}
