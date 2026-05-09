package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MenuListServlet extends HttpServlet {
    // 模拟菜单数据
    private static final List<MenuItem> MENU_ITEMS = new ArrayList<>();

    static {
        MENU_ITEMS.add(new MenuItem("Fried Rice", 8));
        MENU_ITEMS.add(new MenuItem("Fried Noodles", 9));
        MENU_ITEMS.add(new MenuItem("Burger", 10));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 获取查询参数 name
        String name = request.getParameter("name");

        out.println("Menu List:");
        out.println();

        int index = 1;
        for (MenuItem item : MENU_ITEMS) {
            // 支持模糊搜索
            if (name == null || item.getName().contains(name)) {
                out.println(index + ". " + item.getName() + " - $" + (int) item.getPrice());
                index++;
            }
        }
    }
}