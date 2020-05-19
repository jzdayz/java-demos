package io.github.jzdayz.netflix.conductor;

import com.alibaba.fastjson.JSON;
import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleWorker implements Worker {

	private String taskDefName;

	public SampleWorker(String taskDefName) {
		this.taskDefName = taskDefName;
	}

	@Override
	public String getTaskDefName() {
		return taskDefName;
	}

	@Override
	public TaskResult execute(Task task) {
		TaskResult result = new TaskResult(task);
		result.setStatus(TaskResult.Status.COMPLETED);
		log.info("执行任务->"+ JSON.toJSONString(task));

		if (taskDefName.equals("task1")){
			result.getOutputData().put("tt","arg");
		}else{
			System.err.println(task.getInputData().get("arg"));
		}

		//Register the output of the task
		return result;
	}
}