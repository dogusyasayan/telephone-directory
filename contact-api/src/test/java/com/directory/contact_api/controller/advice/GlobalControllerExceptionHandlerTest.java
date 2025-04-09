package com.directory.contact_api.controller.advice;

import com.directory.contact_api.exception.ContactInfoNotFoundException;
import com.directory.contact_api.exception.ContactNotFoundException;
import com.directory.contact_api.exception.enums.ErrorStatus;
import com.directory.contact_api.service.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TestController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@Import(GlobalControllerExceptionHandler.class)
class GlobalControllerExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    @Test
    void it_should_respond_with_404_for_ContactNotFoundException() throws Exception {
        mockMvc.perform(get("/test/contact-not-found"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.exception", is("ContactNotFoundException")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    void it_should_respond_with_404_for_ContactInfoNotFoundException() throws Exception {
        mockMvc.perform(get("/test/contact-info-not-found"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.exception", is("ContactInfoNotFoundException")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }
}

@RestController
class TestController {

    @GetMapping("/test/contact-not-found")
    public void throwContactNotFound() {
        throw new ContactNotFoundException(ErrorStatus.CONTACT_NOT_FOUND);
    }

    @GetMapping("/test/contact-info-not-found")
    public void throwContactInfoNotFound() {
        throw new ContactInfoNotFoundException(ErrorStatus.CONTACT_INFO_NOT_FOUND);

    }
}

