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

        // 1. 先输出固定格式，和老师的示例完全一致
        out.println("Menu List:");
        out.println();

        // 2. 处理 name 参数的所有情况
        String nameParam = request.getParameter("name");
        boolean hasFilter = false;
        String filter = "";

        if (nameParam != null) {
            filter = nameParam.trim();
            // 关键：只有当 filter 不为空字符串时，才开启过滤
            if (!filter.isEmpty()) {
                hasFilter = true;
            }
        }

        // 3. 输出菜单
        int index = 1;
        for (MenuItem item : MENU_ITEMS) {
            // 没有过滤条件，或者名字包含过滤词时，输出
            if (!hasFilter || item.getName().contains(filter)) {
                out.println(index + ". " + item.getName() + " - $" + (int) item.getPrice());
                index++;
            }
        }
    }
}