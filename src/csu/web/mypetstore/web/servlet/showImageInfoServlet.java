package csu.web.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class showImageInfoServlet extends HttpServlet {

    private static final Map<String, String> IMAGE_TOOLTIP_DATA = new HashMap<>();

    static {
        IMAGE_TOOLTIP_DATA.put("FISH", "Ornamental Fish Are Colorful and Calming");
        IMAGE_TOOLTIP_DATA.put("DOG", "loyal Companions, Ideal For Families");
        IMAGE_TOOLTIP_DATA.put("CAT", "Elegant, Independent, and Interactive Cat,HA JI MI~~");
        IMAGE_TOOLTIP_DATA.put("REPTILE", "Smart and lively, Mimicking speech.");
        IMAGE_TOOLTIP_DATA.put("BIRD", "Small birds have melodious songs and charm.");
        IMAGE_TOOLTIP_DATA.put("AV-CB-01", "Great companion for up to 75 years");
        IMAGE_TOOLTIP_DATA.put("AV-SB-02", "Great stress reliever");
        IMAGE_TOOLTIP_DATA.put("FI-FW-01", "Fresh Water fish from Japan");
        IMAGE_TOOLTIP_DATA.put("FI-FW-02", "Fresh Water fish from China");
        IMAGE_TOOLTIP_DATA.put("FI-SW-01", "Salt Water fish from Australia");
        IMAGE_TOOLTIP_DATA.put("FI-SW-02", "Salt Water fish from Australia");
        IMAGE_TOOLTIP_DATA.put("FL-DLH-02", "Friendly house cat, doubles as a princess");
        IMAGE_TOOLTIP_DATA.put("FL-DSH-01", "Great for reducing mouse populations");
        IMAGE_TOOLTIP_DATA.put("K9-BD-01", "Friendly dog from England");
        IMAGE_TOOLTIP_DATA.put("K9-CW-01", "Great companion dog");
        IMAGE_TOOLTIP_DATA.put("K9-DL-01", "Great dog for a Fire Station");
        IMAGE_TOOLTIP_DATA.put("K9-PO-02", "Cute dog from France");
        IMAGE_TOOLTIP_DATA.put("K9-RT-01", "Great family dog");
        IMAGE_TOOLTIP_DATA.put("K9-RT-02", "Great hunting dog");
        IMAGE_TOOLTIP_DATA.put("RP-LI-02", "Friendly green friend");
        IMAGE_TOOLTIP_DATA.put("RP-SN-01", "Doubles as a watch dog");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前端传递的图片 ID
        String id = req.getParameter("id");

        Map<String, Object> response = new HashMap<>();
        if (IMAGE_TOOLTIP_DATA.containsKey(id)) {
            response.put("success", true);
            response.put("message", IMAGE_TOOLTIP_DATA.get(id));
        } else {
            response.put("success", false);
            response.put("message", "提示信息不存在");
        }

        // 返回 JSON 数据
        resp.setContentType("text/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSONString(response));
    }



}
