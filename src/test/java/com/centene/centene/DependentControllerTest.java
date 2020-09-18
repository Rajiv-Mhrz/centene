package com.centene.centene;

import com.centene.centene.domain.Dependent;
import com.centene.centene.domain.Enrollee;
import com.centene.centene.service.DependentService;
import com.centene.centene.service.EnrolleeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DependentControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    DependentService dependentService;

    @MockBean
    EnrolleeService enrolleeService;

    @Test
    public void getAllDependentTest() throws Exception {
        mockMvc.perform(get("/dependent").accept(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void shouldAddValidDependent() throws Exception {

        Enrollee enrollee = new Enrollee();
        enrollee.setEnrolleeId(1L);
        enrollee.setStatus(true);
        enrollee.setName("super user");
        enrollee.setDob(new Date());

        Dependent dependent = new Dependent();
        dependent.setId(1L);
        dependent.setName("Brother");
        dependent.setDob(new Date());

        enrollee.getDependents().add(dependent);

        Mockito.when(enrolleeService.getEnrollee(1L)).thenReturn(Optional.of(enrollee));
        assertTrue(dependentService.addDependent(dependent) != null);

    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionForInvalidDependentnameName() throws Exception {

        Enrollee enrollee = new Enrollee();
        enrollee.setEnrolleeId(1L);
        enrollee.setStatus(true);
        enrollee.setName("Parent");
        enrollee.setDob(new Date());

        Dependent dependent = new Dependent();
        dependent.setId(1L);
        dependent.setName("");
        dependent.setDob(new Date());

        enrollee.getDependents().add(dependent);

        Mockito.when(enrolleeService.getEnrollee(1L)).thenReturn(Optional.of(enrollee));
        dependentService.addDependent(dependent);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionForInvalidDependentnameDob() throws Exception {

        Enrollee enrollee = new Enrollee();
        enrollee.setEnrolleeId(1L);
        enrollee.setStatus(true);
        enrollee.setName("");
        enrollee.setDob(new Date());

        Dependent dependent = new Dependent();
        dependent.setId(1L);
        dependent.setName("Rajiv");

        enrollee.getDependents().add(dependent);

        Mockito.when(enrolleeService.getEnrollee(1L)).thenReturn(Optional.of(enrollee));
        dependentService.addDependent(dependent);
    }

}
