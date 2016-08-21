package by.bsu.travelagency.command;

import by.bsu.travelagency.dao.VacationDAO;
import by.bsu.travelagency.entity.Vacation;
import by.bsu.travelagency.resource.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Михаил on 2/16/2016.
 */
public class DeleteVacationCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(DeleteVacationCommand.class);

    private static final String PARAM_NAME_ID = "id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        VacationDAO vacationDAO = new VacationDAO();
        vacationDAO.delete(id);
        page = ConfigurationManager.getProperty("path.page.admin.panel");
        return page;
    }

}
