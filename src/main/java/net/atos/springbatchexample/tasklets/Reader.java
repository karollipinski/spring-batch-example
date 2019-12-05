package net.atos.springbatchexample.tasklets;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class Reader implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("Start reader ->>>>>");

        // logika biznesowa czytania danych np z pliku / bazy danych

        log.info("End reader <<<<<<<-");
        return RepeatStatus.FINISHED;
    }
}
