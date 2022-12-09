package command;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

public class MemberRegistrationCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("MemberRegistrationCommand ..");

        Dao dao = new Dao();
        String custNo = dao.max_custNo();

        request.setAttribute("custNo", custNo);
    }
}