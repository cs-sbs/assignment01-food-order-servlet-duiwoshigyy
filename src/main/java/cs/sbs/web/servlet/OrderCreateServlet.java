// src/main/java/cs/sbs/web/servlet/OrderCreateServlet.java
package cs.sbs.web.servlet;

import cs.sbs.web.model.Order; // 修正包名：cs.sbs.web.model，不是 cs.sbs.model
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderCreateServlet extends HttpServlet {
    // 全局共享的订单存储（用 ConcurrentHashMap 保证线程安全）
    public static final Map<String, Order> ORDER_MAP = new ConcurrentHashMap<>();
    // 订单ID自增生成器
    public static final AtomicInteger ORDER_ID_GENERATOR = new AtomicInteger(1000);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 1. 获取表单参数
        String customer = request.getParameter("customer");
        String food = request.getParameter("food");
        String quantityStr = request.getParameter("quantity");

        // 2. 异常处理：参数为空
        if (customer == null || customer.isBlank() || food == null || food.isBlank()) {
            out.println("Error: Customer and Food name cannot be empty");
            return;
        }

        // 3. 异常处理：数量非合法数字
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                out.println("Error: quantity must be a valid positive number");
                return;
            }
        } catch (NumberFormatException e) {
            out.println("Error: quantity must be a valid number");
            return;
        }

        // 4. 生成订单ID并保存到全局MAP
        String orderId = String.valueOf(ORDER_ID_GENERATOR.getAndIncrement());
        Order newOrder = new Order(orderId, customer, food, quantity);
        ORDER_MAP.put(orderId, newOrder); // 存入共享存储，供详情页查询

        // 5. 返回成功结果
        out.println("Order Created: " + orderId);
    }
}