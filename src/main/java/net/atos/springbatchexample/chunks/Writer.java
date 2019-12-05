package net.atos.springbatchexample.chunks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.atos.springbatchexample.chunks.entity.Users;
import net.atos.springbatchexample.chunks.repository.UsersRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component(value = "writerChunk")
public class Writer implements ItemWriter<Users>, StepExecutionListener {

    @Autowired
    private UsersRepository repository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Line writer initialized");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Line writer ended");
        return ExitStatus.COMPLETED;
    }

    @Override
    public void write(List<? extends Users> list) throws Exception {
        log.info("Writing user{}", list);
        repository.saveAll(list);
    }
}
