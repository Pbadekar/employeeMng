package com.study.geeks.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "UserCreatereportconfig", description = "This is scheduer configuration")
public @interface ReportConfig {

	@AttributeDefinition(name = "schedulername", type = AttributeType.STRING, description = "Scheduler Name")
	public String GetschedulerName() default "User details report";

	@AttributeDefinition(name = "crone time", type = AttributeType.STRING, description = "Crone time")
	public String Getcronetime() default "abc";
	
	@AttributeDefinition(name ="Scheduler on/off",type=AttributeType.STRING,description="scheduler option",options= {
	@Option(label="ON",value="ON"),
	@Option(label="OFF",value="OFF")
	})
	public String Getschedulerstatus() default "OFF" ;
}
