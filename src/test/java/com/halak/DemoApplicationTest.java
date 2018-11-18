package com.halak;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DemoApplication.class})
public class DemoApplicationTest {

    @Test
    @DisplayName("Should successfully load game context")
    public void contextLoads() {
        // intentionally left blank, if not exceptions are thrown - application was able to bootstrap
    }

}
