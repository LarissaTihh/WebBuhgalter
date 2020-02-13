/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Firm;
import entity.History;
import entity.Order;
import entity.User;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessions.FirmFacade;
import sessions.HistoryFacade;
import sessions.OrderFacade;
import sessions.UserFacade;

/**
 *
 * @author LARISSA
 */
@WebServlet(name = "AdminServlet", urlPatterns = {
   
    "/newOrder",
    "/addOrder",
    "/listFirms",   
    "/showPaymentOrder",
})
public class AdminServlet extends HttpServlet {
@EJB OrderFacade orderFacade;
@EJB FirmFacade firmFacade;
@EJB HistoryFacade historyFacade;
@EJB UserFacade userFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
           request.setAttribute("info", "У Вас нет прав,войдите!");
            request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                        .forward(request, response);
            return;
        }
        User user = (User) session.getAttribute("user");
        if(user == null){
           request.setAttribute("info", "У Вас нет прав,войдите!");
           request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                        .forward(request, response);
            return;
        }
        if(!"admin".equals(user.getLogin())){
           request.setAttribute("info", "У Вас нет прав,зайдите как администратор!");
            request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                        .forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/newOrder":
                request.getRequestDispatcher("/WEB-INF/newOrder.jsp")
                        .forward(request, response);
                break;
            case "/addOrder":
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String year = request.getParameter("year");
                String summ = request.getParameter("summ");
                Order order = new Order(title, author, Integer.parseInt(year), Integer.parseInt(summ));
                orderFacade.create(order);
                request.setAttribute("info", "Счёт создан");
                request.setAttribute("order", order);
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
           
            case "/listFirms":    
                List<Firm> listFirms = firmFacade.findAll();
                request.setAttribute("listFirms", listFirms);
                request.getRequestDispatcher("/listFirms.jsp")
                        .forward(request, response);
                break;
           
            case "/showPaymentOrder":
                List<History> listHistories = historyFacade.findTookOrder();
                request.setAttribute("listHistories", listHistories);
                request.getRequestDispatcher("/WEB-INF/showPaymentOrder.jsp")
                        .forward(request, response);
                break;
          
       
         
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
