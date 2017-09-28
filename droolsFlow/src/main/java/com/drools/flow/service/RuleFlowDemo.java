package com.drools.flow.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.drools.flow.entity.Student;
import com.drools.flow.utils.KnowledgeSessionHelper;

/**
 * @author Administrator
 *	参考--https://nheron.gitbooks.io/droolsonboarding/content/gettingStarted/lesson_4__ruleflow.html
 */
public class RuleFlowDemo 
{
	public static void main(String[] args) 
	{
		KieContainer kieContainer = KnowledgeSessionHelper.createRuleBase();
		KieSession sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSessionForJBPM(kieContainer, "ksession-rules");

		Student student = new Student();
		student.setAge(18);
		student.setName("张三");
		student.setScore(680);
		Student student1 = new Student();
		student1.setAge(19);
		student1.setName("李四");
		student1.setScore(701);
		sessionStatefull.insert(student); 
		sessionStatefull.insert(student1); 
//		【ruleFlowDemo1】--流程文件id
//		String processId = "ruleFlowDemo1";
		String processId = "ruleFlowDemo2";
		sessionStatefull.startProcess(processId);
		sessionStatefull.fireAllRules();
	}
	
	
	
 
}
