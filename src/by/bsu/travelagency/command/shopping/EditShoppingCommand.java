package by.bsu.travelagency.command.shopping;

import by.bsu.travelagency.command.ActionCommand;
import by.bsu.travelagency.command.exception.CommandException;
import by.bsu.travelagency.controller.TravelController;
import by.bsu.travelagency.resource.ConfigurationManager;
import by.bsu.travelagency.service.exception.ServiceException;
import by.bsu.travelagency.service.impl.ShoppingServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class EditShoppingCommand implements ActionCommand {

    /** The Constant LOG. */
    private final static Logger LOG = Logger.getLogger(EditShoppingCommand.class);

    /** The Constant PARAM_NAME_ID. */
    private static final String PARAM_NAME_ID = "id";
    
    /** The Constant PARAM_NAME_NAME. */
    private static final String PARAM_NAME_NAME = "name";
    
    /** The Constant PARAM_NAME_SUMMARY. */
    private static final String PARAM_NAME_SUMMARY = "summary";
    
    /** The Constant PARAM_NAME_DEPARTURE_DATE. */
    private static final String PARAM_NAME_DEPARTURE_DATE = "departure-date";
    
    /** The Constant PARAM_NAME_ARRIVAL_DATE. */
    private static final String PARAM_NAME_ARRIVAL_DATE = "arrival-date";

    /** The Constant PARAM_NAME_DESTINATION_CITY. */
    private static final String PARAM_NAME_DESTINATION_CITY = "destination-city";
    
    /** The Constant PARAM_NAME_SHOPS. */
    private static final String PARAM_NAME_SHOPS = "shops";
    
    /** The Constant PARAM_NAME_LAST_MINUTE. */
    private static final String PARAM_NAME_LAST_MINUTE = "last-minute";
    
    /** The Constant PARAM_NAME_PRICE. */
    private static final String PARAM_NAME_PRICE = "price";
    
    /** The Constant PARAM_NAME_TRANSPORT. */
    private static final String PARAM_NAME_TRANSPORT = "transport";
    
    /** The Constant PARAM_NAME_SERVICES. */
    private static final String PARAM_NAME_SERVICES = "services";
    
    /** The Constant PARAM_NAME_DESCRIPTION. */
    private static final String PARAM_NAME_DESCRIPTION = "description";

    /* (non-Javadoc)
     * @see by.bsu.travelagency.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;


        String id = request.getParameter(PARAM_NAME_ID);
        String name = request.getParameter(PARAM_NAME_NAME);
        String summary = request.getParameter(PARAM_NAME_SUMMARY);
        String departureDate = request.getParameter(PARAM_NAME_DEPARTURE_DATE);
        String arrivalDate = request.getParameter(PARAM_NAME_ARRIVAL_DATE);
        String destinationCityId = request.getParameter(PARAM_NAME_DESTINATION_CITY);
        String shops = request.getParameter(PARAM_NAME_SHOPS);
        String lastMinute = request.getParameter(PARAM_NAME_LAST_MINUTE);
        LOG.debug("Last minute test: " + request.getParameter(PARAM_NAME_LAST_MINUTE));
        String price = request.getParameter(PARAM_NAME_PRICE);
        String transport = request.getParameter(PARAM_NAME_TRANSPORT);
        String services = request.getParameter(PARAM_NAME_SERVICES);
        String description = request.getParameter(PARAM_NAME_DESCRIPTION);
        ShoppingServiceImpl shoppingService = new ShoppingServiceImpl();

        try {
            if ("application/octet-stream".equals(request.getPart("img").getContentType())){
                if (shoppingService.checkEditShopping(id ,name, summary, departureDate, arrivalDate, destinationCityId, shops, lastMinute, price, transport, services, description)) {
                    page = ConfigurationManager.getProperty("path.page.admin.panel");
                }
                else {
                    request.setAttribute("errorEditShoppingPassMessage",
                            TravelController.messageManager.getProperty("message.editshoppingerror"));
                    page = ConfigurationManager.getProperty("path.page.admin.edit.info.shopping");
                }
            }
            else {
                String SAVE_DIR = "images" + File.separator + "shoppings";
                String appPath = request.getServletContext().getRealPath("");
                String savePath = appPath + SAVE_DIR;

                LOG.debug("Save Path = " + savePath);
                Part filePart = request.getPart("img");

                if (shoppingService.checkEditShopping(id ,name, summary, departureDate, arrivalDate, destinationCityId, shops, lastMinute, price, transport, services, description, filePart, savePath)) {
                    page = ConfigurationManager.getProperty("path.page.admin.panel");
                }
                else {
                    request.setAttribute("errorEditShoppingPassMessage",
                            TravelController.messageManager.getProperty("message.editshoppingerror"));
                    page = ConfigurationManager.getProperty("path.page.admin.edit.info.shopping");
                }
            }
        } catch (IOException | ServletException e) {
            throw new CommandException("Failed to get parts from request.", e);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }

}
