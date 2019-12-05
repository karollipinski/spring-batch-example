package net.atos.springbatchexample.chaunks;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInvokerController {
 
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
  //  @Qualifier("userJob")
    Job userJob;


    //http://localhost:8080/run-batch-job
    @RequestMapping("/run-batch-job")
    public String handle() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder()
            								.addString("JobChunkID", "Spring Boot Chunks")
            								.toJobParameters();
            jobLauncher.run(userJob, jobParameters);
            
        return "Batch job has been invoked";
    }
}