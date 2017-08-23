package com.example.sprinter.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private User user;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {
        user = new User(1L, "Name", "Email", "Pass", "Surname");
    }

    @Test
    public void testResponseLoginPageIfNotLogged() throws Exception {
        RequestBuilder requestBuilder = get(
                "/login");
        mockMvc.perform(requestBuilder)
                .andExpect(view().name("login"));
    }

    @Test
    public void testResponseLoginPageIfLogged() throws Exception {
        RequestBuilder requestBuilder = get(
                "/login").sessionAttr("user", user);

        mockMvc.perform(requestBuilder)
                .andExpect(view().name("redirect:/index"))
                .andExpect(status().is(302));
    }
}
