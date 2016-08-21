package by.bsu.travelagency.command;

import by.bsu.travelagency.dao.TripDAO;
import by.bsu.travelagency.dao.UserDAO;
import by.bsu.travelagency.dao.VacationDAO;
import by.bsu.travelagency.entity.Trip;
import by.bsu.travelagency.entity.User;
import by.bsu.travelagency.entity.Vacation;
import by.bsu.travelagency.resource.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Михаил on 2/16/2016.
 */
public class TripFullCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(TripFullCommand.class);

    private static final String PARAM_NAME_ID = "id";
    private static final String PARAM_NAME_ID_USER = "iduser";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        Long iduser = (Long) request.getSession().getAttribute(PARAM_NAME_ID_USER);
        TripDAO tripDAO = new TripDAO();
        Trip trip = tripDAO.findEntityById(id);
        request.setAttribute("trip", trip);
        if (iduser != null) {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findEntityById(iduser);
            request.setAttribute("userProfile", user);
        }
        page = ConfigurationManager.getProperty("path.page.trip.full");
        return page;
    }
}
