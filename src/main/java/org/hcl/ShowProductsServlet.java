package org.hcl;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShowProductsServlet extends HttpServlet {

    GenericDAO<DrinkDTO> drinkDTO;

    public ShowProductsServlet() {
        drinkDTO = new DrinkDAOImpl();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<form action='' method='POST'>");
        out.println("<label>Enter Drink ID: <input type='number' name='drink-id'></input></label>");
        out.println("<input type='submit'>Find Drink</input>");
        out.println("</form>");

        out.println("<h2>Drinks</h2>");
        for (DrinkDTO drink: drinkDTO.getAll()) {
            out.println("<p>" + drink.getId() + ": " + drink.getName() + " It is carbonated:" + drink.isCarbonated() + "</p>");
        }
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("drink-id");
        boolean found=false;

        List<DrinkDTO> drinks= drinkDTO.getAll();
        response.setContentType("text/html");
        for(DrinkDTO drink: drinks){
            if(Integer.valueOf(id).equals(drink.getId())){
                found=true;
                PrintWriter out = response.getWriter();
                out.println(drink.getName() + "was found. It is carbonated: "+ drink.isCarbonated());
            }
        }
        if(!found){
            PrintWriter out = response.getWriter();
            out.println("No drink found under entered ID");
        }

    }
}
