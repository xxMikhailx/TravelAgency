package by.bsu.travelagency.command;

import by.bsu.travelagency.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Михаил on 2/16/2016.
 */
public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.index");
// уничтожение сессии
        HttpSession session = request.getSession(true);
        String lang = null;
        if (session.getAttribute("lang") != null) {
            lang = (String) session.getAttribute("lang");
        }
        session.invalidate();
        /*if (lang != null) {
            session.setAttribute("lang", lang);
        }*/
        return page;
    }
}