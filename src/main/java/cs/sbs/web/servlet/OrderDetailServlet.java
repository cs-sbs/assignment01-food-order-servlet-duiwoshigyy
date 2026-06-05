package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            out.println("Error: Order not found");
            return;
        }

        int orderId;
        try {
            orderId = Integer.parseInt(pathInfo.substring(1));
        } catch (Exception e) {
            out.println("Error: Order not found");
            return;
        }

        Order target = null;
        for (Order o : OrderCreateServlet.orderList) {
            if (o.getOrderId() == orderId) {
                target = o;
                break;
            }
        }

        if (target == null) {
            out.println("Error: Order not found");
            return;
        }

        out.println("Order Detail");
        out.println();
        out.println("Order ID: " + target.getOrderId());
        out.println("Customer: " + target.getCustomer());
        out.println("Food: " + target.getFood());
        out.println("Quantity: " + target.getQuantity());
    }
}