package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface StudentService {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
