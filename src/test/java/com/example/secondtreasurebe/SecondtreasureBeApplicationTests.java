package com.example.secondtreasurebe;

import com.example.secondtreasurebe.controller.StaffController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SecondtreasureBeApplicationTests {
    @Autowired
    private StaffController staffController;

    @Test
    void contextLoads() throws Exception{
        assertThat(staffController).isNotNull();
    }

}
