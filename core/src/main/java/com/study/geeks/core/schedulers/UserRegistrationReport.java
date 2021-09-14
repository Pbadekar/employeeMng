package com.study.geeks.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.geeks.core.config.ReportConfig;

@Component(immediate=true,service=Runnable.class)
@Designate(ocd=ReportConfig.class)
public class UserRegistrationReport implements Runnable{

	@Reference Scheduler scheduler;
	
	int schedulerID;
	private String configparameter;
	private static final Logger Log= LoggerFactory.getLogger(UserRegistrationReport.class);
	
	@Activate
	protected void activate(ReportConfig config) {
		configparameter=config.GetschedulerName();
		schedulerID=configparameter.hashCode();
		Log.info("Scheduler activated "+configparameter);
		addScheduler(config);
	}
	
	@Modified
	protected void modified(ReportConfig config) {
		configparameter=config.GetschedulerName();
		schedulerID=configparameter.hashCode();
		Log.info("Scheduler activated "+configparameter);
		addScheduler(config);
	}
	
	@Deactivate
	protected void deactivate(ReportConfig config) {
		removescheduler();
	}
	
	private void removescheduler() {
		// TODO Auto-generated method stub
		Log.info("removinf scheduler "+ schedulerID);
		scheduler.unschedule(String.valueOf(schedulerID));
	}

	private void addScheduler(ReportConfig config) {
		// TODO Auto-generated method stub
		if (config.Getschedulerstatus().equals("ON")) {
			
			ScheduleOptions spots= scheduler.EXPR(config.Getcronetime());
			spots.name(String.valueOf(schedulerID));
			spots.canRunConcurrently(false);
			scheduler.schedule(this, spots);
			Log.info("Scheduler added successfully");
		}else {
			removescheduler();
			Log.info("No scheduler added");
		}
	
	}

	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		Log.info("Void Rune is executing...");
	}

}
