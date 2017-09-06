//package com.example.sprinter.user;
//
//import com.example.sprinter.project.ProjectService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//
//import java.util.HashSet;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
//public class UserDetailsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private ProjectService projectService;
//
//    private User userDetails;
//
//    @Before
//    public void setUp(){
//        userDetails = new User(1L, "Name", "Email", "Password", "Surname", new HashSet<>(), new HashSet<>(), new UserDetail());
//        userDetails.setId(1L);
//
//        when(userService.getByLogin(anyString())).thenReturn(userDetails);
//    }
//
//    @Test
//    public void testRenderUserProfilePage() throws Exception {
//        RequestBuilder requestBuilder = get("/userDetails").sessionAttr("userDetails", userDetails);
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().is(200))
//                .andExpect(view().name("userDetails-profile"));
//    }
//
//
//    @Test
//    public void testRenderUserEditPage() throws Exception {
//        RequestBuilder requestBuilder = get("/userDetails/password").sessionAttr("userDetails", userDetails);
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().is(200))
//                .andExpect(view().name("userDetails-edit-profile"));
//    }
//
//    @Test
//    public void testEditPasswordWhenPasswordIsNotCorrect() throws Exception {
//        RequestBuilder requestBuilder = post("/userDetails/password")
//                .sessionAttr("userDetails", userDetails)
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("password","a")
//                .param("confirm", "dupa");
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().is(200))
//                .andExpect(view().name("userDetails-edit-profile"))
//                .andExpect(model().attribute("error", "Wrong password"));
//    }
//
//    @Test
//    public void testResponseLoginPageIfNotLogged() throws Exception {
//        RequestBuilder requestBuilder = get(
//                "/login");
//        mockMvc.perform(requestBuilder)
//                .andExpect(view().name("login"));
//    }
//
//    @Test
//    public void testResponseLoginPageIfLogged() throws Exception {
//        RequestBuilder requestBuilder = get(
//                "/login").sessionAttr("userDetails", userDetails);
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(view().name("redirect:/"))
//                .andExpect(status().is(302));
//    }
//}
