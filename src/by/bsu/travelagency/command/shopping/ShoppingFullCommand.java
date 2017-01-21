package by.bsu.travelagency.command.shopping;

import by.bsu.travelagency.command.ActionCommand;
import by.bsu.travelagency.command.exception.CommandException;
import by.bsu.travelagency.dao.jdbc.JdbcShoppingDAO;
import by.bsu.travelagency.dao.jdbc.JdbcUserDAO;
import by.bsu.travelagency.dao.exception.DAOException;
import by.bsu.travelagency.entity.Shopping;
import by.bsu.travelagency.entity.User;
import by.bsu.travelagency.resource.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Михаил on 2/16/2016.
 */
public class ShoppingFullCommand implements ActionCommand {

    /** The Constant LOG. */
    private final static Logger LOG = Logger.getLogger(ShoppingFullCommand.class);

    /** The Constant PARAM_NAME_ID. */
    private static final String PARAM_NAME_ID = "id";
    
    /** The Constant PARAM_NAME_ID_USER. */
    private static final String PARAM_NAME_ID_USER = "iduser";

    /* (non-Javadoc)
     * @see by.bsu.travelagency.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        Long iduser = (Long) request.getSession().getAttribute(PARAM_NAME_ID_USER);
        JdbcShoppingDAO shoppingDAO = new JdbcShoppingDAO();
        Shopping shopping = null;
        try {
            shopping = shoppingDAO.findEntityById(id);
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        if (id == shopping.getId()) {
            request.setAttribute("shopping", shopping);
            if (iduser != null) {
                JdbcUserDAO userDAO = new JdbcUserDAO();
                User user = null;
                try {
                    user = userDAO.findEntityById(iduser);
                } catch (DAOException e) {
                    throw new CommandException(e);
                }
                request.setAttribute("userProfile", user);
            }
            page = ConfigurationManager.getProperty("path.page.shopping.full");
        } else {
            page = ConfigurationManager.getProperty("path.page.main");
        }
        return page;
    }
}