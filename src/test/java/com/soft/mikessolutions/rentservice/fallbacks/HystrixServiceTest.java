package com.soft.mikessolutions.rentservice.fallbacks;
//TODO
/*
import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import com.soft.mikessolutions.rentservice.assemblers.DefaultUserResourceAssembler;
import com.soft.mikessolutions.rentservice.clients.UserClient;
import com.soft.mikessolutions.rentservice.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HystrixServiceTest {

    @MockBean
    UserClient userClient;

    @Autowired
    DefaultUserResourceAssembler defaultUserResourceAssembler;

    private static final Long TEST_ID = 1L;
    private final User SUCCESSFUL_USER = new User(1L, "Johnie", "Doesky",
            null, "johniedoesky@doesky.com", "111-222-333", "pass", "DEFAULT");

    @Before
    public void setup() {
        resetHystrix();
        openCircuitBreakerAfterOneFailingRequest();
    }

    @Test
    public void circuitBreakerClosedOnSuccess() throws IOException, InterruptedException {

        when(userClient.getUserById(TEST_ID))
                .thenReturn(defaultUserResourceAssembler.toResource(SUCCESSFUL_USER));

        userClient.getUserById(TEST_ID);
        HystrixCircuitBreaker circuitBreaker = getCircuitBreaker();
        Assert.assertTrue(circuitBreaker.allowRequest());

        verify(userClient, times(1))
                .getUserById(any(Long.class));
    }

    private void resetHystrix(){
        Hystrix.reset();
    }

    private void warmUpCircuitBreaker() {
        userClient.getUserById(TEST_ID);
    }

    public static HystrixCircuitBreaker getCircuitBreaker() {
        return HystrixCircuitBreaker.Factory.getInstance(getCommandKey());
    }

    private static HystrixCommandKey getCommandKey() {
        return HystrixCommandKey.Factory.asKey("getUserById");
    }

    private void openCircuitBreakerAfterOneFailingRequest() {

        ConfigurationManager.getConfigInstance()
                .setProperty("hystrix.command.getUserById.circuitBreaker.requestVolumeThreshold", 1);
    }


}
*/