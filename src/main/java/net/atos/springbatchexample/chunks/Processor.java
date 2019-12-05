package net.atos.springbatchexample.chunks;

import lombok.extern.slf4j.Slf4j;
import net.atos.springbatchexample.chunks.entity.Users;
import net.atos.springbatchexample.chunks.repository.UsersRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component(value = "processorChunk")
public class Processor implements ItemProcessor<Users, Users>, StepExecutionListener {

    @Autowired
    private UsersRepository repository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Line processor initialized");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Line processor ended");
        return ExitStatus.COMPLETED;
    }

    @Override
    public Users process(Users users) throws Exception {
        Optional<Users> userFromDB = repository.findById(users.getUserId());
        if (userFromDB.isPresent()) {
            users.setAccount(users.getAccount()
                                  .add(userFromDB.get()
                                                 .getAccount()));
        }
        return users;
    }
}
