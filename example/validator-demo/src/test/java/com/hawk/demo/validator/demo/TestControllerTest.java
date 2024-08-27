package com.hawk.demo.validator.demo;

import com.hawk.demo.validator.demo.model.Param;
import com.hawk.demo.validator.demo.util.JsonUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

@SpringBootTest
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class TestControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void testPost() {
        // 执行POST请求并验证结果
        Param param = Param.builder()
                .category("x")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/test")
                .content(JsonUtil.serialize(param).getBytes())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}