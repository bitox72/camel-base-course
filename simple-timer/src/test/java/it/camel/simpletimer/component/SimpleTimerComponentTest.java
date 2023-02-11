package it.camel.simpletimer.component;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static it.camel.simpletimer.constants.AppConstants.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@CamelSpringBootTest
@UseAdviceWith
public class SimpleTimerComponentTest {

    @Autowired
    CamelContext camelContext;
    @EndpointInject("mock:result")
    MockEndpoint mockEndpoint;


    @Test
    public void test_timer() throws Exception {
            mockEndpoint.expectedMinimumMessageCount(1);
            mockEndpoint.expectedBodiesReceived(MESSAGE);

        AdviceWith.adviceWith(camelContext,ID, routeBuilder->{
            routeBuilder.weaveAddLast().to(mockEndpoint);
        });

        camelContext.start();

        mockEndpoint.assertIsSatisfied();
    }



}