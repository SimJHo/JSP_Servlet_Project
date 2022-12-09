package command;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.TeacherDto;



public class GTeacherCommand implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Dao dao = new Dao();
		List<TeacherDto> dtos = dao.list();
		
		request.setAttribute("teacher", dtos);
	}
}