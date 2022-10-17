package com.ohmycode.todolist;

import com.ohmycode.todolist.models.entities.Address;
import com.ohmycode.todolist.models.entities.Todo;
import com.ohmycode.todolist.models.entities.User;
import com.ohmycode.todolist.repos.TodoRepository;
import com.ohmycode.todolist.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OhMyCodePruebaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OhMyCodePruebaApplication.class, args);
    }

    @Bean
    CommandLineRunner init(TodoRepository todoRepo, UserRepository userRepo) {
        if (todoRepo.findAll().size() == 0) {
            generateData(todoRepo, userRepo);
        }

        return args -> {
        };
    }

    private void generateData(TodoRepository todoRepo, UserRepository userRepo) {
        final String[] names = {"David", "Marta", "Susana", "John", "Julie", "Jennifer", "Helen", "Rachel"};
        final String[] countryNames = {"Spain", "Portugal", "France"};

        for (int i = 0; i < names.length; i++) {
            Address address = new Address();
            address.setCity("city");
            address.setZipcode("08100");
            address.setStreet("street");
            address.setCountry(countryNames[i % countryNames.length]);

            User user = new User();
            user.setName(names[i]);
            user.setAddress(address);
            user.setPassword("1234");
            user.setUsername(names[i]);

            userRepo.save(user);
        }
        userRepo.flush();

        for (User user : userRepo.findAll()) {
            for (int i = 0; i < 6; i++) {
                Todo todo = new Todo();
                todo.setUser(user);
                todo.setCompleted(i % 2 != 0);
                todo.setTitle(user.getName().concat(String.valueOf(i)));
                todoRepo.save(todo);
            }
        }
    }


}
