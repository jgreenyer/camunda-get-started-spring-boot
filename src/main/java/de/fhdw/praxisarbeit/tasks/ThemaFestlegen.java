package de.fhdw.praxisarbeit.tasks;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ThemaFestlegen implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String gewaehltesThema = execution.getVariable("gewaehltesthema").toString();
		String thema = execution.getVariable("thema"+gewaehltesThema).toString();
		execution.setVariable("thema", thema);
	}
	
	

}
