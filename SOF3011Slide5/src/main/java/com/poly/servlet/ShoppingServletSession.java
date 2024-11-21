/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.bean.BeanCart;


@WebServlet("/ShoppingServletSession")
public class ShoppingServletSession extends HttpServlet {
final private String homeShop="/views/Shopping.jsp";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {      
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CardServletSession</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Shopping Cart</h1>");
            String action = request.getParameter("action");

            if(action.equals("Add To Cart")){
                HttpSession session = request.getSession(true);
                BeanCart cart = (BeanCart) session.getAttribute("CART");
                if(cart == null){
                    cart = new BeanCart();
                }
                String item = request.getParameter("cboCard");
                cart.addToCart(item);
                session.setAttribute("CART", cart);
                RequestDispatcher rd = request.getRequestDispatcher(homeShop);
                rd.forward(request, response);
            }else if(action.equals("View Cart")){
               HttpSession session = request.getSession();
               if(session == null){
                   out.print("Session not exist");
               }else{
                   BeanCart cart = (BeanCart) session.getAttribute("CART");
                   if(cart ==null){
                       out.println("Khong co hang ma coi cai gi");
                   }else{
                       Enumeration items = cart.getCart().elements();
                       out.println("Cac mon hang da chon:<br>");
                       out.println("<form action='ShoppingServletSession'>");
                       out.println("<table border='1'><tr>");
                       out.println("<td>TT</td>");
                       out.println("<td>Items</td>");
                       out.println("<td>Check</td></tr>");
                       int tt=0;
                       while(items.hasMoreElements()){
                           String mon = (String)items.nextElement();
                           tt++;
                           out.println("<tr><td>"+tt+"</td>");
                           out.println("<td>"+mon+"</td>");
                           out.println("<td> <input type='checkbox' name='chk' value='"+mon+"' /> </td></tr>");
                       }
                       out.println("<tr><td></td><td> <a href='ShoppingServletSession?action=Back'>Back</a></td>");
                       out.println("<td> <input type='submit' value='Delete' name='action' /> </td></tr>");
                       out.println("</table>");
                       out.println("</form>");
                   }                   
               }
            }else if(action.equals("Back")){
                RequestDispatcher rd = request.getRequestDispatcher(homeShop);
                rd.forward(request, response);
            }else if(action.equals("Delete")){
                HttpSession session = request.getSession();
                if(session == null){
                    out.println("Session not exist");
                }else{
                    BeanCart cart = (BeanCart) session.getAttribute("CART"); 
                    if(cart == null){
                        out.println("hang dau ma xoa");
                    }else{
                        String[] items = request.getParameterValues("chk");
                        for(int i=0;i<items.length;i++){
                            cart.deleteItem(items[i]);
                        }
                        session.setAttribute("CART", cart);
                        RequestDispatcher rd = request.getRequestDispatcher("ShoppingServletSession?action=View Cart");
                        rd.forward(request, response);
                    }
                }                                               
            }
            out.println("</body>");
            out.println("</html>");          
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
