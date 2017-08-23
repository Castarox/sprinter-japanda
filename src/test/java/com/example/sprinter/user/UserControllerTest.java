package com.example.sprinter.user;

import com.example.sprinter.project.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ProjectService projectService;

    private User user;

    @Before
    public void setUp(){
        user = new User("Name", "Email", "Password", "Surname");
        user.setId(1L);

        when(userService.getByLogin(anyString(), anyString())).thenReturn(user);
    }

    @Test
    public void testRenderUserProfilePage() throws Exception {
        RequestBuilder requestBuilder = get("/user").sessionAttr("user", user);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(view().name("user-profile"));
    }


    @Test
    public void testRenderUserEditPage() throws Exception {
        RequestBuilder requestBuilder = get("/user/password").sessionAttr("user", user);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(view().name("user-edit-profile"));
    }

    @Test
    public void testEditPasswordWhenPasswordIsNotCorrect() throws Exception {
        RequestBuilder requestBuilder = post("/user/password")
                .sessionAttr("user", user)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("password","a")
                .param("confirm", "dupa");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(view().name("user-edit-profile"))
                .andExpect(model().attribute("error", "Wrong password"));
    }

    @Test
    public void testEditPasswordWhenPasswordIsCorrect() throws Exception {
        RequestBuilder requestBuilder = post("/user/password")
                .sessionAttr("user", user)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("password","password12")
                .param("confirm", "password12");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(302))
                .andExpect(model().attribute("success", "success"))
                .andExpect(redirectedUrl("/user?success=success"));

        verify(userService, times(1)).saveUser(anyObject());
        verifyNoMoreInteractions(userService);
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
