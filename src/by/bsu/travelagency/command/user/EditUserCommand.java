package by.bsu.travelagency.command.user;

import by.bsu.travelagency.command.ActionCommand;
import by.bsu.travelagency.command.exception.CommandException;
import by.bsu.travelagency.controller.TravelController;
import by.bsu.travelagency.entity.User;
import by.bsu.travelagency.resource.ConfigurationManager;
import by.bsu.travelagency.service.exception.ServiceException;
import by.bsu.travelagency.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(EditUserCommand.class);

    private static final String PARAM_NAME_ID = "id";
    
    private static final String PARAM_NAME_LOGIN = "login";
    
    private static final String PARAM_NAME_PASSWORD = "password";
    
    private static final String PARAM_NAME_EMAIL = "email";
    
    private static final String PARAM_NAME_NAME = "name";
    
    private static final String PARAM_NAME_SURNAME = "surname";
    
    private static final String PARAM_NAME_ROLE = "role";
    
    private static final String PARAM_NAME_DISCOUNT = "discount";
    
    private static final String PARAM_NAME_MONEY = "money";

    /* (non-Javadoc)
     * @see by.bsu.travelagency.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;

        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        int role = Integer.parseInt(request.getParameter(PARAM_NAME_ROLE));
        double discount = Double.parseDouble(request.getParameter(PARAM_NAME_DISCOUNT));
        double money = Double.parseDouble(request.getParameter(PARAM_NAME_MONEY));
        UserServiceImpl userService = new UserServiceImpl();

        try {
            if (pass.isEmpty()) {
                if (userService.checkEditUser(id, login, email, name, surname, role, discount, money)) {
                    page = ConfigurationManager.getProperty("path.page.admin.panel");
                } else {
                    request.setAttribute("errorEditUserMessage",
                            TravelController.messageManager.getProperty("message.editusererror"));
                    page = createUserEditPage(request, id);
                }
            } else {
                if (userService.checkEditUser(id, login, pass, email, name, surname, role, discount, money)) {
                    page = ConfigurationManager.getProperty("path.page.admin.panel");
                } else {
                    request.setAttribute("errorEditUserMessage",
                            TravelController.messageManager.getProperty("message.editusererror"));
                    page = createUserEditPage(request, id);
                }
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }

        return page;
    }

    private String createUserEditPage(HttpServletRequest request, Long id) throws CommandException {
        String page = null;
        UserServiceImpl userService = new UserServiceImpl();
        User user = null;
        try {
            user = userService.findEntityById(id);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (id == user.getId()) {
            request.setAttribute("userProfile", user);
            page = ConfigurationManager.getProperty("path.page.admin.edit.info.user");
        } else {
            page = ConfigurationManager.getProperty("path.page.admin.panel");
        }
        return page;
    }

}
