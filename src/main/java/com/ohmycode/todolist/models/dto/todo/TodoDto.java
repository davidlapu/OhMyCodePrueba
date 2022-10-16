package com.ohmycode.todolist.models.dto.todo;

import lombok.Data;

@Data
public class TodoDto {
    private Long id;
    private String title;
    private Boolean completed;
    private Long userId;
}
