package by.bsu.travelagency.dao;

import by.bsu.travelagency.dao.exception.DAOException;
import by.bsu.travelagency.entity.User;
import by.bsu.travelagency.entity.UserOrderNumber;

import java.util.List;

public interface UserDAO extends GenericDAO<Long, User> {
    /**
     * Find all users.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<User> findAllUsers() throws DAOException;

    /**
     * Find all users with order count.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    List<UserOrderNumber> findAllUsersWithOrderCount() throws DAOException;

    /**
     * Find user by name.
     *
     * @param name the name
     * @return the user
     * @throws DAOException the DAO exception
     */
    User findUserByName(String name) throws DAOException;

    /**
     * Find money by user id.
     *
     * @param id the id
     * @return the money
     * @throws DAOException the DAO exception
     */
    double findMoneyByUserId(Long id) throws DAOException;

    /**
     * Find password by user id.
     *
     * @param id the id
     * @return the string
     * @throws DAOException the DAO exception
     */
    String findPasswordByUserId(Long id) throws DAOException;

    /**
     * Update password by user id.
     *
     * @param id the id
     * @param password the password
     * @return true, if successful
     * @throws DAOException the DAO exception
     */
    boolean updatePasswordByUserId(Long id, String password) throws DAOException;

    /**
     * Update user balance.
     *
     * @param id the id
     * @param money the money
     * @return true, if successful
     * @throws DAOException the DAO exception
     */
    boolean updateUserBalance(Long id, double money) throws DAOException;

    /**
     * Update user balance addition.
     *
     * @param id the id
     * @param moneyToAdd the money to add
     * @return true, if successful
     * @throws DAOException the DAO exception
     */
    boolean updateUserBalanceAddition(Long id, double moneyToAdd) throws DAOException;

}
