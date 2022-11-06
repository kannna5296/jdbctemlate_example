package com.example.usejdbctemplate.batch

import com.example.usejdbctemplate.batch.tasklet.TaskImportTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableBatchProcessing
@Configuration
class TaskImportBatch {

    @Autowired
    lateinit var taskImportTasklet: TaskImportTasklet
    @Autowired
    lateinit var jobBuilderFactory: JobBuilderFactory
    @Autowired
    lateinit var stepBuilderFactory: StepBuilderFactory

    @Bean
    fun job(step1: Step): Job {
        return jobBuilderFactory.get("job")
            .incrementer(RunIdIncrementer())
            .start(step1)
            .build()
    }

    @Bean
    fun step1(): Step {
        return stepBuilderFactory["step1"]
            .tasklet(taskImportTasklet)
            .build()
    }
}