package com.ohmycode.todolist.services;

import com.ohmycode.todolist.exceptions.UserNotFoundException;
import com.ohmycode.todolist.models.dto.todo.TodoDto;
import com.ohmycode.todolist.models.dto.todo.TodoDtoConverter;
import com.ohmycode.todolist.models.entities.Todo;
import com.ohmycode.todolist.models.entities.User;
import com.ohmycode.todolist.repos.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepo;
    private final TodoDtoConverter todoDtoConverter;


    public List<Todo> findAll() {
        return todoRepo.findAll();
    }

    public List<Todo> findAll(int page, int size, Sort.Direction direction, String sortingParam) {
        Pageable pageable = PageRequest.of(page, size, direction, sortingParam);

        return todoRepo.findAll(pageable).toList();
    }

    public List<Todo> findAll(int page, int size, Sort.Direction direction, String sortingParam, Optional<String> title, Optional<String> username) {
        Pageable pageable = PageRequest.of(page, size, direction, sortingParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        Todo todo = new Todo();

        if (title.isPresent()) {
            todo.setTitle(title.get());
            exampleMatcher = exampleMatcher.withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        if (username.isPresent()) {
            User user = new User();
            user.setUsername(username.get());
            todo.setUser(user);
        }

        Example<Todo> example = Example.of(todo, exampleMatcher);

        return todoRepo.findAll(example, pageable).toList();
    }

    public Todo insert(Todo todo) {
        return todoRepo.save(todo);
    }

    public Todo insert(TodoDto todoDto) {
        Todo todo = todoDtoConverter.convertTodoDtoToEntity(todoDto);
        return todoRepo.save(todo);
    }

    public Todo update(TodoDto todoDto) {
        Todo todo = todoRepo.findById(todoDto.getId()).orElseThrow(UserNotFoundException::new);
        todo = todoDtoConverter.convertTodoDtoToEntity(todoDto, todo);

        return todoRepo.save(todo);
    }

    public void delete(Todo todo) {
        todoRepo.delete(todo);
    }

    public void deleteById(Long id) {
        todoRepo.deleteById(id);
        todoRepo.flush();
    }
}
