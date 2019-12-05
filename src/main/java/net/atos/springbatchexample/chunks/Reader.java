package net.atos.springbatchexample.chunks;

import lombok.extern.slf4j.Slf4j;
import net.atos.springbatchexample.chunks.entity.Users;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

@Slf4j
public class Reader extends FlatFileItemReader<Users> implements StepExecutionListener {

    public Reader(Resource resource) {
        setResource(resource);

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"userId", "name", "address", "amount"});
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);

        BeanWrapperFieldSetMapper<Users> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Users.class);

        DefaultLineMapper<Users> usersDefaultLineMapper = new DefaultLineMapper<>();
        usersDefaultLineMapper.setLineTokenizer(lineTokenizer);
        usersDefaultLineMapper.setFieldSetMapper(fieldSetMapper);
        setLineMapper(usersDefaultLineMapper);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Line reader initialized");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Line reader ended");
        return ExitStatus.COMPLETED;
    }
}
