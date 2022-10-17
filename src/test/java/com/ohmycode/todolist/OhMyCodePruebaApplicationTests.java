package com.ohmycode.todolist;

import com.ohmycode.todolist.repos.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
//@Sql({"/test-data.sql"})
class OhMyCodePruebaApplicationTests {
	@MockBean private TodoRepository todoRepo;

	@Test
	void contextLoads() {
		System.out.println(todoRepo.findAll());
	}

}
