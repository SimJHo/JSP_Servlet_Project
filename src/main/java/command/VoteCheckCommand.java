package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.VotecheckDto;

public class VoteCheckCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Dao dao = new Dao();
		
		List<VotecheckDto> check = dao.voteCheck();
		
		request.setAttribute("check", check);
	}

}
