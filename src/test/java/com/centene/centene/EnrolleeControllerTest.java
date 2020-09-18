package com.centene.centene;

import com.centene.centene.domain.Enrollee;
import com.centene.centene.service.EnrolleeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnrolleeControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    EnrolleeService enrolleeService;


    @Test
    public void getAllEnrollee() throws Exception {
        mockMvc.perform(get("/enrollee").accept(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void shouldAddValidEnrollee() {

        Enrollee enrollee = new Enrollee();
        enrollee.setEnrolleeId(1L);
        enrollee.setStatus(true);
        enrollee.setName("super user");
        enrollee.setDob(new Date());
        enrollee.setPhone("89898989");
        assertTrue(enrolleeService.addEnrollee(enrollee) != null);

    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionForInvalidEnrolleeName() {

        Enrollee enrollee = new Enrollee();
        enrollee.setEnrolleeId(1L);
        enrollee.setStatus(true);
        enrollee.setName("");
        enrollee.setDob(new Date());
        enrollee.setPhone("89898989");

        enrolleeService.addEnrollee(enrollee);

    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionForInvalidEnrolleeDob() {

        Enrollee enrollee = new Enrollee();
        enrollee.setEnrolleeId(1L);
        enrollee.setStatus(true);
        enrollee.setName("sample name");
        enrollee.setPhone("89898989");
        enrolleeService.addEnrollee(enrollee);

    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionForInvalidEnrolleePhone() {

        Enrollee enrollee = new Enrollee();
        enrollee.setEnrolleeId(1L);
        enrollee.setStatus(true);
        enrollee.setName("sample name");
        enrollee.setDob(new Date());
        enrollee.setPhone("");
        enrolleeService.addEnrollee(enrollee);

    }

}
