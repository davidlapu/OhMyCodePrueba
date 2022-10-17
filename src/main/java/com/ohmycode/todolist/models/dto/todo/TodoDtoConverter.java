package com.ohmycode.todolist.models.dto.todo;

import com.ohmycode.todolist.exceptions.UserNotFoundException;
import com.ohmycode.todolist.models.entities.Todo;
import com.ohmycode.todolist.models.entities.User;
import com.ohmycode.todolist.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoDtoConverter {
    private final UserService userService;

    public Todo convertTodoDtoToEntity(TodoDto dto) {
        Todo todo = new Todo();
        return convertTodoDtoToEntity(dto, todo);
    }

    public Todo convertTodoDtoToEntity(TodoDto dto, Todo todo) {
        User user = userService.findById(dto.getUserId()).orElseThrow(UserNotFoundException::new);

        todo.setId(todo.getId());
        todo.setTitle(dto.getTitle());
        todo.setCompleted(dto.getCompleted());
        todo.setUser(user);

        return todo;
    }
}
