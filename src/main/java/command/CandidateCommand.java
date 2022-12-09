package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.CandidateDto;

public class CandidateCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Dao dao = new Dao();
		
		List<CandidateDto> candi = dao.candidate();
		
		request.setAttribute("candi", candi);
	}

}
