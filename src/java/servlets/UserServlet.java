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
import java.util.Date;
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
@WebServlet(name = "UserServlet", urlPatterns = {
    "/showTakeOnBook",
    "/takeOnBook",
    "/returnOnBook",})
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @EJB
    private OrderFacade orderFacade;
    @EJB
    private FirmFacade firmFacade;
    @EJB
    private HistoryFacade historyFacade;
    @EJB
    private UserFacade userFacade;

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
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        HttpSession session = request.getSession(false);
        if (session == null) {
            request.setAttribute("info", "У Вас нет прав,войдите!");
            request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                    .forward(request, response);
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("info", "У Вас нет прав,войдите!");
            request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                    .forward(request, response);
            return;
        }
        switch (path) {
            case "/showTakeOnBook":
                List<Order> listOrders = orderFacade.findAll();
                List<Firm> listFirms = firmFacade.findAll();
                request.setAttribute("listOrders", listOrders);
                request.setAttribute("listFirms", listFirms);
                request.getRequestDispatcher("/WEB-INF/showTakeOnOrder.jsp")
                        .forward(request, response);
                break;
            case "/takeOnOrder":
                String orderId = request.getParameter("orderId");
                String firmId = request.getParameter("firmId");
                Order order = orderFacade.find(Long.parseLong(orderId));
                Firm firm = firmFacade.find(Long.parseLong(firmId));
                History history = new History();
                history.setOrder(order);
                history.setFirm(firm);
                history.setTakeOn(new Date());
                historyFacade.create(history);
                request.setAttribute("info",
                        "Счёт \""
                        + order.getTitle()
                        + "\" выдана читателю: "
                        + firm.getTitle()
                        + " " + firm.getAddress()
                );
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
            case "/paymentOnOrder":
                String historyId = request.getParameter("historyId");
                history = historyFacade.find(Long.parseLong(historyId));
                history.setPaymentDate(new Date());
                historyFacade.edit(history);
                request.setAttribute("info", "Счёт \"" + history.getOrder().getTitle() + "\" оплачен");
                request.getRequestDispatcher("/showPaymentOrder")
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
