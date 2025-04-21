package com.springboot.admin.controller;

import com.springboot.admin.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Root Controller")
@Import(SecurityConfig.class)
@WebMvcTest(MainController.class)
class MainControllerTest {
    private final MockMvc mvc;
    public MainControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @WithMockUser(username = "tester", roles = "USER")
    @DisplayName("[GET] Root Page -> Forwarding Article Management Page")
    @Test
    void whenRequestingRootView_thenForwardsToArticleManagementView() throws Exception {
        // When & Then
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("forward:/management/articles"))
                .andExpect(forwardedUrl("/management/articles"));
    }
}