package com.example.usejdbctemplate.batch.tasklet

import com.example.usejdbctemplate.batch.entity.TaskEntity
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component


@Component
@StepScope
class TaskImportTasklet : Tasklet {

    @Value("#{jobParameters['TASK_ID']}")
    val taskId: String? = null

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {

        println("taskId: $taskId")
        if (!taskId.isNullOrEmpty()) {
            val task = jdbcTemplate.queryForObject("SELECT * FROM task WHERE id = ?", TaskEntity.mapper, taskId)
            println("取得できたタスクID:" + task?.name)
        } else {
            throw Exception("引数を与えて実行して下さい。")
        }

        return RepeatStatus.FINISHED
    }
}