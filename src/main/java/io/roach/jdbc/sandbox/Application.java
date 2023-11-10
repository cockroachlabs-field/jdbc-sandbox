package io.roach.jdbc.sandbox;

import java.util.Arrays;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = {
        TransactionAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        DataSourceAutoConfiguration.class
})
@ComponentScan(basePackages = "io.roach.jdbc.sandbox")
public class Application implements CommandLineRunner {
    public static final String SPRING_JDBC_GET_PARAMETER_TYPE_IGNORE
            = "spring.jdbc.getParameterType.ignore";

    @Autowired
    private ProductInventory productInventory;

    @Override
    public void run(String... args) {
        LinkedList<String> argsList = new LinkedList<>(Arrays.asList(args));

        Scenario scenario = Scenario.a;

        while (!argsList.isEmpty()) {
            String arg = argsList.pop();
            if ("--scenario".equals(arg)) {
                scenario = Scenario.valueOf(argsList.pop());
            }
        }

        System.out.printf("Test scenarios include (use --scenario to change):\n");
        Arrays.stream(Scenario.values()).sequential().forEach(
                s -> System.out.printf("\t'%s' (%s)\n", s.name(), s.description()));

        System.out.printf("Running test using scenario '%s' (%s)\n", scenario.name(), scenario.description());
        System.out.printf("%s = %s\n",
                SPRING_JDBC_GET_PARAMETER_TYPE_IGNORE,
                System.getProperty(SPRING_JDBC_GET_PARAMETER_TYPE_IGNORE, "(undefined)"));

        productInventory.createInventory(scenario);
    }

    public static void main(String[] args) {
        Arrays.asList(args).forEach(s -> {
            if ("--ignore".equals(s)) {
                System.setProperty(SPRING_JDBC_GET_PARAMETER_TYPE_IGNORE, "true");
            }
        });

        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}

