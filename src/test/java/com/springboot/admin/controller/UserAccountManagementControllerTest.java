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
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Controller - User Account Management")
@Import(SecurityConfig.class)
@WebMvcTest(UserAccountManagementController.class)
class UserAccountManagementControllerTest {
    private final MockMvc mvc;
    public UserAccountManagementControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }
    @WithMockUser(username = "tester", roles = "USER")
    @DisplayName("[GET] User Account Management Page - 정상 호출")
    @Test
    void whenRequestingUserAccountManagementView_thenReturnsUserAccountManagementView() throws Exception {
        // When & Then
        mvc.perform(get("/management/user-accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("management/user-accounts"));
    }
}