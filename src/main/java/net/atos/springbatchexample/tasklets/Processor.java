package net.atos.springbatchexample.tasklets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class Processor implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        log.info("Start processor ->>>>>");

        // logika biznesowa przetwarzania danych, modyfikacja, obliczenia

        log.info("End procesor <<<<<<<-");

        return RepeatStatus.FINISHED;
    }
}
