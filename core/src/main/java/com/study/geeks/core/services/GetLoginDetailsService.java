package com.study.geeks.core.services;

public interface GetLoginDetailsService {

	String VerifyUser(String id, String pass);

	String CreateUser(String fname, String lname, String add, String id, String pwd);

}
