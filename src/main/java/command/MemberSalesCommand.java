package command;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.SalesDto;

import java.util.List;

public class MemberSalesCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("MemberSalesCommand ..");

        Dao dao = new Dao();
        List<SalesDto> salesDtoList = dao.sales();

        request.setAttribute("salesList",salesDtoList);

    }
}