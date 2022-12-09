package command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.MemberDto;

public class MemberContentCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String custNo = request.getParameter("custNo");

        Dao dao = new Dao();
        MemberDto dto = dao.content(custNo);

        request.setAttribute("content", dto);
    }
}