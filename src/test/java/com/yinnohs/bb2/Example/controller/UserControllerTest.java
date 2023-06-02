package com.yinnohs.bb2.Example.controller;

import static org.mockito.ArgumentMatchers.anyLong;

import com.yinnohs.bb2.Example.application.controller.UserController;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.repository.UserRepository;
import com.yinnohs.bb2.Example.application.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;


import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(
        exclude = {
                DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        }
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureWebClient
@WithMockUser
public class UserControllerTest {



    @Autowired
    private WebTestClient webClient;
    @MockBean
    UserRepository userRepository;

    @BeforeEach
    public void setup(){}

    @Test
    public void getOneUserByIdHappyPathTest(){
        User expectedUser = this.getOneMockUserDTO();
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.of(expectedUser));

        this.webClient
                .get()
                .uri("/api/v1/user/0")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.data.id").isNotEmpty()
                .jsonPath("$.data.name").isEqualTo("jhon")
                .jsonPath("$.data.surname").isEqualTo("doe")
                .jsonPath("$data.email").isEqualTo("jhon@doe.com");

        Mockito.verify(userRepository.findById(1l));
    }

    private User getOneMockUserDTO (){
        User user =  new User();
        user.setUserId(0);
        user.setName("jhon");
        user.setSurname("doe");
        user.setEmail("jhon@doe.com");
        user.setCreationDate(LocalDate.now());

        return user;
    }
}
