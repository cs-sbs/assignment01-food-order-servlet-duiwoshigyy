package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OrderCreateServlet extends HttpServlet {
    // 全局订单存储
    public static List<Order> orderList = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 获取表单参数
        String customer = request.getParameter("customer");
        String food = request.getParameter("food");
        String quantityStr = request.getParameter("quantity");

        // 异常处理：参数为空
        if (customer == null || customer.isBlank() || food == null || food.isBlank() || quantityStr == null || quantityStr.isBlank()) {
            out.println("Error: All fields are required");
            return;
        }

        // 异常处理：数量不是合法数字
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                out.println("Error: quantity must be a valid number");
                return;
            }
        } catch (NumberFormatException e) {
            out.println("Error: quantity must be a valid number");
            return;
        }

        // 创建订单
        Order order = new Order(customer, food, quantity);
        orderList.add(order);

        // 返回结果
        out.println("Order Created: " + order.getOrderId());
    }
}