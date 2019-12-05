package net.atos.springbatchexample.tasklets;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class TasklestConfig {

    private JobBuilderFactory jobs;

    private StepBuilderFactory steps;

    private Writer writer;

    private Processor processor;

    private Reader reader;

    @Bean
    protected Step read() {
        return steps.get("read")
                    .tasklet(reader)
                    .build();
    }

    @Bean
    protected Step write() {
        return steps.get("write")
                    .tasklet(writer)
                    .build();
    }

    @Bean
    protected Step process() {
        return steps.get("process")
                    .tasklet(processor)
                    .build();
    }

    @Bean
    public Job job() {
        return jobs.get("tasklestJob")
                   .start(read())
                   .next(process())
                   .next(write())
                   .build();
    }

}
