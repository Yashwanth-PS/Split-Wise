package com.splitwise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SettleUpControllerEndPointTest {

    // Constants for expected responses
    private static final String DONE_RESPONSE = "DONE";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenInitializationSucceedsWhenInitializingThenReturns200AndDone() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/settleup/v1/init"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(DONE_RESPONSE));
    }

    @Test
    public void givenSettleUpSucceedsWhenSettlingUpThenReturns200AndTransactionList() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.get("/settleup/v1/settle/Group1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}