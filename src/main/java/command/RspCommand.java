package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.RspDto;

public class RspCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String rspInput = request.getParameter("rsp");
		
		System.out.println(rspInput);
		
		Dao dao = new Dao();
		
		List<RspDto> rspResult = dao.rsp(rspInput);
		
		request.setAttribute("rsp", rspResult);
		
	}

}
