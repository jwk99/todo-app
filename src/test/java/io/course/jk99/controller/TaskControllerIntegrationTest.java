package io.course.jk99.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.course.jk99.model.Task;
import io.course.jk99.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class TaskControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository repo;

    @Test
    void httpGet_returnsGivenTask() throws Exception {
        //given
        int id = repo.save(new Task("foo", LocalDateTime.now())).getId();

        //when + then
        mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void httpGet_returnsAllTasks() throws Exception
    {
        //given
        repo.save(new Task("intgrTest1", LocalDateTime.now()));
        repo.save(new Task("intgrTest2", LocalDateTime.now()));

        //when + then
        mockMvc.perform(get("/tasks"))
                .andExpect(status().is2xxSuccessful());

    }

//    @Test
//    void httpPost_createsTask() throws Exception
//    {
//        mockMvc.perform(post("/tasks")
//            .content(asJsonString(new Task("test", LocalDateTime.now())))
//            .contentType(MediaType.APPLICATION_JSON)
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isCreated());
//
//    }
//
//    public static String asJsonString(Object obj)
//    {
//        try{
//            return new ObjectMapper().writeValueAsString(obj);
//        }catch (Exception e)
//        {
//            throw new RuntimeException(e);
//        }
//    }
//
}
