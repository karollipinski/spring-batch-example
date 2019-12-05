package net.atos.springbatchexample.chunks;

import net.atos.springbatchexample.chunks.entity.Users;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ChunkConfig extends JobExecutionListenerSupport {

    private JobBuilderFactory jobs;

    private StepBuilderFactory steps;

    private Writer writer;

    private Processor processor;

    @Value("${input.file}")
    Resource resource;

    public ChunkConfig(JobBuilderFactory jobs,
                       StepBuilderFactory steps,
                       @Qualifier(value = "writerChunk") Writer writer,
                       @Qualifier(value = "processorChunk") Processor processor) {
        this.jobs = jobs;
        this.steps = steps;
        this.writer = writer;
        this.processor = processor;
    }

    @Bean(name = "userJob")
    public Job userJob() {
        return jobs.get("user-job")
                   .incrementer(new RunIdIncrementer())
                   .listener(this)
                   .start(steps())
                   .build();
    }

    @Bean
    private Step steps() {
        return steps.get("step-1")
                    .<Users, Users>chunk(1)
                    .reader(new Reader(resource))
                    .processor(processor)
                    .writer(writer)
                    .build();
    }

}
