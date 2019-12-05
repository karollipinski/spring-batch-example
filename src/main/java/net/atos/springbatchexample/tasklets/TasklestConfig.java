package net.atos.springbatchexample.tasklets;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class TasklestConfig {

    private JobBuilderFactory job;

    private StepBuilderFactory step;

    Writer writer;

    Processor processor;

    Reader reader;




}
