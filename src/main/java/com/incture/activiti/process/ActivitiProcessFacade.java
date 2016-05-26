package com.incture.activiti.process;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;

public class ActivitiProcessFacade {
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	private String filename = "/home/jeetendra/workspace/BritaniaWorkflow/src/main/resources/diagrams/LAP_Process.bpmn";
	
	public static void main(String[] args) throws FileNotFoundException {
		
		ActivitiProcessFacade processFacade = new ActivitiProcessFacade();
		String processFileName = processFacade.filename;
		ActivitiRule activitiRule2 = processFacade.activitiRule;
		if(processFileName!=null && !processFileName.isEmpty()){
			System.out.println(processFileName);
		} else{
			System.out.println("processFileName Null");
			return;
		}
		
		if (activitiRule2!=null){
			System.out.println(activitiRule2);
		}else{
			System.out.println("activitiRule2 Null");
			return;
		}
		
		
		RepositoryService repositoryService = activitiRule2.getRepositoryService();
		if (repositoryService!=null){
		repositoryService.createDeployment().addInputStream("LAP_Process.bpmn",
				new FileInputStream(processFileName)).deploy();
		}else{
			System.out.println("Repository Service Null");
			return;
		}
		
		RuntimeService runtimeService = activitiRule2.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LAP_Process", variableMap);
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
	}
}
