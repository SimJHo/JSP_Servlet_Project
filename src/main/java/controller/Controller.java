package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CandidateCommand;
import command.Command;
import command.GRegistCommand;
import command.GStudentCommand;
import command.GSumCommand;
import command.GTeacherCommand;
import command.MemberContentCommand;
import command.MemberInsertCommand;
import command.MemberListCommand;
import command.MemberRegistrationCommand;
import command.MemberSalesCommand;
import command.MemberUpdateCommand;
import command.RspCommand;
import command.VoteCheckCommand;
import command.VoteCommand;
import command.VoteCountCommand;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() ..");
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() ..");
		actionDo(request,response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
		System.out.println("actionDo() ..");
		
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		Command command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		System.out.println("경로 확인:" + uri + " : " + conPath + " : " + com );
	
		// 투표
		if(com.equals("/main.do")) {
			System.out.println("main.do..");
			viewPage ="main.jsp";
		}else if(com.equals("/candidate.do")) {
			System.out.println("candidate.do..");
			command = new CandidateCommand();
			command.execute(request, response);
			viewPage ="candidate.jsp";
		}else if(com.equals("/vote.do")) {
			System.out.println("vote.do..");
			command = new VoteCommand();
			command.execute(request, response);
			viewPage ="main.jsp";
		}else if(com.equals("/vote_check.do")) {
			System.out.println("vote_check.do..");
			command = new VoteCheckCommand();
			command.execute(request, response);
			viewPage ="vote_check.jsp";
		}else if(com.equals("/vote_count.do")) {
			System.out.println("vote_count.do..");
			command = new VoteCountCommand();
			command.execute(request, response);
			viewPage ="vote_count.jsp";
		}
		//쇼핑
		if (com.equals("/list.do")) {
            System.out.println("/list.do ..");
            command = new MemberListCommand();
            command.execute(request, response);
            viewPage = "list.jsp";

        } else if (com.equals("/registration.do")) {
            System.out.println("/registration.do ..");
            command = new MemberRegistrationCommand();
            command.execute(request, response);
            viewPage = "insert.jsp";
        } else if (com.equals("/insert.do")) {
            System.out.println("/insert.do ..");
            command = new MemberInsertCommand();
            command.execute(request, response);
            viewPage = "list.do";
        } else if (com.equals("/sales.do")) {
            System.out.println("/sales.do ..");
            command = new MemberSalesCommand();
            command.execute(request, response);
            viewPage = "sales.jsp";
        } else if (com.equals("/content.do")) {
            System.out.println("/content.do ..");
            command = new MemberContentCommand();
            command.execute(request, response);
            viewPage = "content.jsp";
        } else if (com.equals("/update.do")) {
            System.out.println("/update.do ..");
            command = new MemberUpdateCommand();
            command.execute(request, response);
            viewPage = "list.do";
        }
		//골프
		else if(com.equals("/golf_teacher.do")) {
			System.out.println("디버깅문구");
			command = new GTeacherCommand();
			command.execute(request, response);
			viewPage ="golf_teacher.jsp";
		}else if(com.equals("/golf_student.do")) {
			System.out.println("/student.do ..");
			command = new GStudentCommand();
			command.execute(request, response);
			viewPage ="golf_student.jsp";
		}if(com.equals("/golf_regist_view.do")) {
			System.out.println("golf_regist_view.do..");
			viewPage ="golf_regist.jsp";
		}else if(com.equals("/golf_regist.do")) {
			System.out.println("/golf_regist.do ..");
			command = new GRegistCommand();
			command.execute(request, response);
			viewPage ="main.jsp";
		}else if(com.equals("/golf_sum.do")) {
			System.out.println("/sum.do ..");
			command = new GSumCommand();
			command.execute(request, response);
			viewPage ="golf_sum.jsp";
		}
		
		//가위바위보
		else if(com.equals("/rsp.do")) {
			System.out.println("rsp.do..");
			command = new RspCommand();
			command.execute(request, response);
			viewPage ="rsp_result.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
	
}
