package com.clink.blog.vm;

import java.util.ArrayList;
import java.util.List;

public class ResultVm {
	public boolean isSuccess;
	public Object  resultSet;
	public List<String> resultMessages = new ArrayList<>();
	
	public boolean getIsSuccess() {
		return isSuccess;
	}
 
	public Object getResultSet() {
		return resultSet;
	}
 
	public List<String> getResultMessages() {
		return resultMessages;
	}
 

}
