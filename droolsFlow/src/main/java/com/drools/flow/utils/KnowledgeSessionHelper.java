package com.drools.flow.utils;

import org.kie.api.KieServices;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

public class KnowledgeSessionHelper 
{
	public static KieContainer createRuleBase()
	{
//		KieServices kieService = KieServices.Factory.get();
//		KieContainer containeer = kieService.getKieClasspathContainer();
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		return kieContainer;
	}
	
	
	public static StatelessKieSession getStatelessKnowdgeSession(KieContainer containeer,String name)
	{
		StatelessKieSession session = containeer.newStatelessKieSession(name);
		return session;
	}
	public static KieSession getStatefullKnowdgeSession(KieContainer containeer,String name)
	{
		KieSession session = containeer.newKieSession(name);
		return session;
	}
	
	public static KieSession getStatefulKnowledgeSessionForJBPM(KieContainer kieContainer, String sessionName) 
	{
		KieSession session = KnowledgeSessionHelper.getStatefullKnowdgeSession(kieContainer,sessionName);
		session.addEventListener(new ProcessEventListener() {
	          @Override
	          public void beforeVariableChanged(ProcessVariableChangedEvent arg0) {
	
	          }
	
	          @Override
	          public void beforeProcessStarted(ProcessStartedEvent arg0) {
	              System.out.println("Process Name "+arg0.getProcessInstance().getProcessName()+" has been started");
	
	
	          }
	
	          @Override
	          public void beforeProcessCompleted(ProcessCompletedEvent arg0) {
	              // TODO Auto-generated method stub
	
	          }
	
	          @Override
	          public void beforeNodeTriggered(ProcessNodeTriggeredEvent arg0) {
	              // TODO Auto-generated method stub
	
	          }
	
	          @Override
	          public void beforeNodeLeft(ProcessNodeLeftEvent arg0) {
                  System.out.println("Node Name "+ arg0.getNodeInstance().getNodeName()+" has been left");        
	
	          }
	
	          @Override
	          public void afterVariableChanged(ProcessVariableChangedEvent arg0) {
	              // TODO Auto-generated method stub
	
	          }
	
	          @Override
	          public void afterProcessStarted(ProcessStartedEvent arg0) {
	
	          }
	
	          @Override
	          public void afterProcessCompleted(ProcessCompletedEvent arg0) {
	              System.out.println("Process Name "+arg0.getProcessInstance().getProcessName()+" has stopped");
	
	
	          }
	
	          @Override
	          public void afterNodeTriggered(ProcessNodeTriggeredEvent arg0) {
	                  System.out.println("Node Name "+ arg0.getNodeInstance().getNodeName()+" has been entered");        
	          }
	
	          @Override
	          public void afterNodeLeft(ProcessNodeLeftEvent arg0) {
	           }
	      });
        return session;
    }
	
}
