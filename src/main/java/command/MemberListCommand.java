package command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.MemberDto;

import java.util.List;

public class MemberListCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        Dao dao = new Dao();
        List<MemberDto> dtoList = dao.list4();

        request.setAttribute("list", dtoList);
    }
}