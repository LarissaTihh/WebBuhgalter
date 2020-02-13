/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Utils.EncryptPass;
import entity.Firm;
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
@WebServlet(name = "loginServlet", urlPatterns = {
    "/showLogin",
    "/login",
    "/logout",
    "/newFirm",
    "/addFirm",
    "/listOrders",})
public class loginServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    @EJB
    private FirmFacade firmFacade;
    @EJB
    private OrderFacade orderFacade;
    @EJB
    private HistoryFacade historyFacade;

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
        switch (path) {
            case "/showLogin":
                request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                        .forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if (login == null || password == null) {
                    request.setAttribute("info", "Неправильный логин или пароль!");
                    request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                            .forward(request, response);
                    break;
                }
                User user = userFacade.findByLogin(login);
                if (user == null) {
                    request.setAttribute("info", "Неправильный логин или пароль!");
                    request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                            .forward(request, response);
                    break;
                }
                EncryptPass encryptPass = new EncryptPass();
                password = encryptPass.getEncryptPass(password, user.getSalts());
                if (!password.equals(user.getPassword())) {
                    request.setAttribute("info", "Неправильный логин или пароль!");
                    request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                            .forward(request, response);
                    break;
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                request.setAttribute("info", "Привет, " + login + "!");
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;

            case "/logout":
                session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                request.setAttribute("info", "Вы вышли");
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;

            case "/newFirm":
                request.getRequestDispatcher("/WEB-INF/newFirm.jsp")
                        .forward(request, response);
                break;
            case "/addFirm":
                String title = request.getParameter("title");
                String address = request.getParameter("address");
                String email = request.getParameter("email");
                login = request.getParameter("login");
                password = request.getParameter("password");
                if (title == null || address == null
                        || email == null || login == null
                        || password == null) {
                    request.setAttribute("info", "Заполните все поля!");
                    request.setAttribute("title", title);
                    request.setAttribute("address", address);
                    request.setAttribute("email", email);
                    request.setAttribute("login", login);
                    request.setAttribute("password", password);
                    request.getRequestDispatcher("/newFirm")
                            .forward(request, response);
                }
                Firm firm = null;
                user = null;
                try {
                    firm = new Firm(title, address, email);
                    firmFacade.create(firm);
                    encryptPass = new EncryptPass();
                    String salts = encryptPass.getSalts();
                    password = encryptPass.getEncryptPass(password, salts);
                    user = new User(login, password, salts, firm);
                    userFacade.create(user);
                } catch (Exception e) {
                    if (firm != null) {
                        firmFacade.remove(firm);
                    }
                    if (user != null) {
                        userFacade.remove(user);
                    }
                    request.setAttribute("info", "Пользователя создать не удалось");
                    request.getRequestDispatcher("/newFirm")
                            .forward(request, response);
                }

                request.setAttribute("info", "Пользователь создан");
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);

                break;
            case "/listOrders":
                List<Order> listOrders = orderFacade.findAll();
                request.setAttribute("listOrders", listOrders);
                request.getRequestDispatcher("/listOrders.jsp")
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
