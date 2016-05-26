package org.activiti.designer.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.FileInputStream;

import org.activiti.engine.task.Task;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestLAPProcess {

	private String filename = "/home/jeetendra/workspace/BritaniaWorkflow/src/main/resources/diagrams/LAP_Process.bpmn";

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	public void startProcess() throws Exception {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		repositoryService.createDeployment().addInputStream("LAP_Process.bpmn20.xml",
				new FileInputStream(filename)).deploy();
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LAP_Process", variableMap);
		assertNotNull(processInstance.getId());
		List<Task> tasks = activitiRule.getTaskService().createTaskQuery().taskCandidateUser("Jeetendra").list();
		System.out.println(tasks.size());
		for (Iterator<Task> iterator = tasks.iterator(); iterator.hasNext();) {
			Task task = (Task) iterator.next();
			Map<String, Object> localVariables = task.getTaskLocalVariables();
			System.out.println(localVariables.values());
		}
			
//		activitiRule.getTaskService().getVariable("name");
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
	}
}