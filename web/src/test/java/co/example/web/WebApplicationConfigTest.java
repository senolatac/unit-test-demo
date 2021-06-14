package co.example.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:13
 */
@ExtendWith(SpringExtension.class)
class WebApplicationConfigTest
{
    // lazy test
    @Test
    public void testContextLoads()
    {
        assert true;
    }
}
