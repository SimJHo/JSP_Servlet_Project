package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.StudentDto;



public class GStudentCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		Dao dao = new Dao();
		List<StudentDto> dtos = dao.list2();

		request.setAttribute("student", dtos);
	}
}