package it.camel.simpletimer.component;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static it.camel.simpletimer.constants.AppConstants.ID;
import static it.camel.simpletimer.constants.AppConstants.MESSAGE;

@Component
public class SimpleTimerComponent extends RouteBuilder {



    @Override
    public void configure() throws Exception {
        //must set camel.springboot.main-run-controller=true to start a camel thread
        from("timer:testTimer?period=2000")
                .routeId(ID)
                .setBody(constant(MESSAGE))
                .log(LoggingLevel.INFO,"${body}");
    }
}
