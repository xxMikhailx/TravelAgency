package by.bsu.travelagency.dao;

import by.bsu.travelagency.dao.exception.DAOException;
import by.bsu.travelagency.entity.Order;
import by.bsu.travelagency.entity.OrderTourInfo;

import java.sql.Date;
import java.util.List;

public interface OrderDAO extends GenericDAO<Long, Order> {
    /**
     * Find all user orders by user id.
     *
     * @param userId the user id
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<OrderTourInfo> findAllUserOrdersByUserId(Long userId) throws DAOException;

    /**
     * Find all user orders by user id after now.
     *
     * @param userId the user id
     * @param nowDate the today's date
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<OrderTourInfo> findAllUserOrdersByUserIdAfterNow(Long userId, Date nowDate) throws DAOException;

    /**
     * Find all user orders by user id before now.
     *
     * @param userId the user id
     * @param nowDate the today's date
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<OrderTourInfo> findAllUserOrdersByUserIdBeforeNow(Long userId, Date nowDate) throws DAOException;

    /**
     * Find departure date by id.
     *
     * @param id the id
     * @return the date
     * @throws DAOException the DAO exception
     */
    Date findDepartureDateById(Long id) throws DAOException;

    /**
     * Find total price by id.
     *
     * @param id the id
     * @return the int
     * @throws DAOException the DAO exception
     */
    int findTotalPriceById(Long id) throws DAOException;

    /**
     * Find userId by OrderId.
     *
     * @param id the id
     * @return the int
     * @throws DAOException the DAO exception
     */
    long findUserIdByOrderId(Long id) throws DAOException;
}