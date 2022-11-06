package com.example.usejdbctemplate.batch.tasklet

import com.example.usejdbctemplate.batch.entity.TaskEntity
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class TaskImportTasklet : Tasklet {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {

        val task = jdbcTemplate.queryForObject("SELECT * FROM task WHERE id = ?", TaskEntity.mapper, 1)
        println(task?.name)

        return RepeatStatus.FINISHED;
    }
}