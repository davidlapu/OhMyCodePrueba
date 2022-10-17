package com.ohmycode.todolist;

import com.ohmycode.todolist.controllers.TodoController;
import com.ohmycode.todolist.repos.TodoRepository;
import com.ohmycode.todolist.repos.UserRepository;
import com.ohmycode.todolist.services.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static com.ohmycode.todolist.utils.PageableAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {TodoController.class})
//@Sql({"/test-data.sql"})
class TodoControllerTests {
    private final int PAGE_SIZE = 10;
    @MockBean
    private TodoService todoService;
    @MockBean
    private TodoRepository todoRepo;
    @MockBean
    private UserRepository userRepo;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void evaluatesPageableParameter() throws Exception {

        mockMvc.perform(get("/todo")
                        .param("page", "0")
                        .param("sortingParam", "title")
                        .param("sortingDirection", "desc"))
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(todoRepo).findAll(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageNumber(0);
        assertThat(pageable).hasPageSize(PAGE_SIZE);
        assertThat(pageable).hasSort("title", Sort.Direction.DESC);
    }

    @Test
    void evaluatesFilterName() throws Exception {
        mockMvc.perform(get("/todo")
                        .param("username", "david"))
                .andExpect(status().isOk());
    }
}
