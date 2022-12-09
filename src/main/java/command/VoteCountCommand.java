package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.VotecountDto;

public class VoteCountCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Dao dao = new Dao();
		
		List<VotecountDto> count = dao.voteCount();
		
		request.setAttribute("count", count);
	}

}
