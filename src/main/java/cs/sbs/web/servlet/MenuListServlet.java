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

        // 关键修复：获取参数并处理所有情况
        String nameParam = request.getParameter("name");
        String filter = null;

        if (nameParam != null) {
            // 去除首尾空格
            filter = nameParam.trim();
            // 如果是空字符串，视为无过滤
            if (filter.isEmpty()) {
                filter = null;
            }
        }

        out.println("Menu List:");
        out.println();

        int index = 1;
        for (MenuItem item : MENU_ITEMS) {
            // 只有当filter不为null时，才进行过滤；否则显示全部
            if (filter == null || item.getName().contains(filter)) {
                out.println(index + ". " + item.getName() + " - $" + (int) item.getPrice());
                index++;
            }
        }
    }
}