package by.bsu.travelagency.dao;

import by.bsu.travelagency.controller.TravelController;
import by.bsu.travelagency.entity.Transport;
import by.bsu.travelagency.entity.User;
import by.bsu.travelagency.entity.Vacation;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михаил on 2/24/2016.
 */
public class VacationDAO extends AbstractDAO<Long, Vacation> {

    private final static Logger LOG = Logger.getLogger(VacationDAO.class);
    private static final String SQL_SELECT_ALL_VACATIONS = "SELECT idvacation,name,summary,description,departure_date,arrival_date,price,last_minute,hotel,destination_city,destination_country,transport,services,path_image FROM vacations";
    private static final String SQL_SELECT_VACATION_BY_ID = "SELECT idvacation,name,summary,description,departure_date,arrival_date,price,last_minute,hotel,destination_city,destination_country,transport,services,path_image FROM vacations WHERE idvacation=?";
    private static final String SQL_SELECT_LAST_VACATIONS = "SELECT idvacation,name,summary,description,departure_date,arrival_date,price,last_minute,hotel,destination_city,destination_country,transport,services,path_image FROM vacations ORDER BY idvacation DESC LIMIT 6";

    public List<Vacation> findAllVacations() {
        List<Vacation> vacations = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        try {
            cn = TravelController.connectionPool.getConnection();
            st = cn.createStatement();
            ResultSet resultSet =
                    st.executeQuery(SQL_SELECT_ALL_VACATIONS);
            while (resultSet.next()) {
                Vacation vacation = new Vacation();
                vacation.setId(resultSet.getLong("idvacation"));
                vacation.setName(resultSet.getString("name"));
                vacation.setSummary(resultSet.getString("summary"));
                vacation.setDescription(resultSet.getString("description"));
                vacation.setDepartureDate(resultSet.getDate("departure_date"));
                vacation.setArrivalDate(resultSet.getDate("arrival_date"));
                vacation.setPrice(resultSet.getInt("price"));
                vacation.setLastMinute(resultSet.getBoolean("last_minute"));
                vacation.setHotel(resultSet.getString("hotel"));
                vacation.setDestinationCity(resultSet.getString("destination_city"));
                vacation.setDestinationCountry(resultSet.getString("destination_country"));
                vacation.setTransport(Transport.valueOf(resultSet.getString("transport")));
                vacation.setServices(resultSet.getString("services"));
                vacation.setPathImage(resultSet.getString("path_image"));
                vacations.add(vacation);
            }
        } catch (SQLException e) {
            LOG.error("SQL exception (request or table failed): " + e);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            closeStatement(st);
            closeConnection(cn);
// код возвращения экземпляра Connection в пул
        }
        return vacations;
    }

    public List<Vacation> selectLastVacations() {
        List<Vacation> vacations = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        try {
            cn = TravelController.connectionPool.getConnection();
            st = cn.createStatement();
            ResultSet resultSet =
                    st.executeQuery(SQL_SELECT_LAST_VACATIONS);
            while (resultSet.next()) {
                Vacation vacation = new Vacation();
                vacation.setId(resultSet.getLong("idvacation"));
                vacation.setName(resultSet.getString("name"));
                vacation.setSummary(resultSet.getString("summary"));
                vacation.setDescription(resultSet.getString("description"));
                vacation.setDepartureDate(resultSet.getDate("departure_date"));
                vacation.setArrivalDate(resultSet.getDate("arrival_date"));
                vacation.setPrice(resultSet.getInt("price"));
                vacation.setLastMinute(resultSet.getBoolean("last_minute"));
                vacation.setHotel(resultSet.getString("hotel"));
                vacation.setDestinationCity(resultSet.getString("destination_city"));
                vacation.setDestinationCountry(resultSet.getString("destination_country"));
                vacation.setTransport(Transport.valueOf(resultSet.getString("transport")));
                vacation.setServices(resultSet.getString("services"));
                vacation.setPathImage(resultSet.getString("path_image"));
                vacations.add(vacation);
            }
        } catch (SQLException e) {
            LOG.error("SQL exception (request or table failed): " + e);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            closeStatement(st);
            closeConnection(cn);
// код возвращения экземпляра Connection в пул
        }
        return vacations;
    }

    public void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection(Connection cn){
        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Vacation findEntityById(Long id) {
        Vacation vacation = new Vacation();
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = TravelController.connectionPool.getConnection();
            ps = cn.prepareStatement(SQL_SELECT_VACATION_BY_ID);
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                vacation.setId(resultSet.getLong("idvacation"));
                vacation.setName(resultSet.getString("name"));
                vacation.setSummary(resultSet.getString("summary"));
                vacation.setDescription(resultSet.getString("description"));
                vacation.setDepartureDate(resultSet.getDate("departure_date"));
                vacation.setArrivalDate(resultSet.getDate("arrival_date"));
                vacation.setPrice(resultSet.getInt("price"));
                vacation.setLastMinute(resultSet.getBoolean("last_minute"));
                vacation.setHotel(resultSet.getString("hotel"));
                vacation.setDestinationCity(resultSet.getString("destination_city"));
                vacation.setDestinationCountry(resultSet.getString("destination_country"));
                vacation.setTransport(Transport.valueOf(resultSet.getString("transport")));
                vacation.setServices(resultSet.getString("services"));
                vacation.setPathImage(resultSet.getString("path_image"));
            }
        } catch (SQLException e) {
            LOG.error("SQL exception (request or table failed): " + e);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            closeStatement(ps);
            closeConnection(cn);
// код возвращения экземпляра Connection в пул
        }
        return vacation;
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Vacation entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Vacation entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Vacation update(Vacation entity) {
        throw new UnsupportedOperationException();
    }
}