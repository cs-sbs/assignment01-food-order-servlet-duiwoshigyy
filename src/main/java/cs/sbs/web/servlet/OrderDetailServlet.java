// src/main/java/cs/sbs/web/servlet/OrderDetailServlet.java
package cs.sbs.web.servlet;

import cs.sbs.web.model.Order; // 修正包名：cs.sbs.web.model
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 1. 解析路径参数（/order/1001 → 提取1001）
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.split("/").length < 2) {
            out.println("Error: Invalid order ID");
            return;
        }

        String orderId = pathInfo.split("/")[1];
        // 2. 从共享的ORDER_MAP中查询订单（不再自己初始化，保证数据一致）
        Order order = OrderCreateServlet.ORDER_MAP.get(orderId);

        // 3. 异常处理：查询不到订单
        if (order == null) {
            out.println("Error: Order ID " + orderId + " not found");
            return;
        }

        // 4. 输出订单详情
        out.println("Order Detail");
        out.println("Order ID: " + order.getOrderId());
        out.println("Customer: " + order.getCustomer());
        out.println("Food: " + order.getFood());
        out.println("Quantity: " + order.getQuantity());
    }
}