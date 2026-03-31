package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuListServlet extends HttpServlet {
    // 模拟菜单数据
    private static final List<MenuItem> MENU_LIST = new ArrayList<>();

    static {
        MENU_LIST.add(new MenuItem("Fried Rice", 8));
        MENU_LIST.add(new MenuItem("Fried Noodles", 9));
        MENU_LIST.add(new MenuItem("Burger", 10));
        MENU_LIST.add(new MenuItem("Pizza", 12));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 获取查询参数（按菜名搜索）
        String keyword = request.getParameter("name");
        List<MenuItem> resultList = MENU_LIST;

        // 过滤逻辑
        if (keyword != null && !keyword.isEmpty()) {
            resultList = MENU_LIST.stream()
                    .filter(item -> item.getName().contains(keyword))
                    .collect(Collectors.toList());
        }

        // 响应结果
        out.println("Menu List:");
        for (int i = 0; i < resultList.size(); i++) {
            MenuItem item = resultList.get(i);
            out.println((i + 1) + ". " + item.getName() + " - $" + item.getPrice());
        }
    }
}