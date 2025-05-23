package com.springboot.admin.controller;

import com.springboot.admin.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Controller - Admin members")
@Import(SecurityConfig.class)
@WebMvcTest(AdminAccountController.class)
class AdminAccountControllerTest {
    private final MockMvc mvc;
    public AdminAccountControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }
    @WithMockUser(username = "tester", roles = "USER")
    @DisplayName("[GET] Admin members Page - 정상 호출")
    @Test
    void whenRequestingAdminMembersView_thenReturnsAdminMembersView() throws Exception {
        // When & Then
        mvc.perform(get("/admin/members"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("admin/members"));
    }
}