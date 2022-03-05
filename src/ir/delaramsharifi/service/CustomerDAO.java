package ir.delaramsharifi.service;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import ir.delaramsharifi.entity.TblCustomer;
import ir.delaramsharifi.utility.PasswordGenerator;

import javax.lang.model.element.Name;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    PasswordGenerator passwordGenerator;
    List<TblCustomer> customers;
    ConnectionSql connectionSql = new ConnectionSql();


    public List<TblCustomer> getAllCustomers() {
        Connection connection = connectionSql.getConnection();
        customers = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from tblcustomer");
            customers = extractResultSetCustomers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<TblCustomer> searchCustomersByFamily(String family) {
        Connection connection = connectionSql.getConnection();
        customers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from tblcustomer c where c.family like ? ");
            preparedStatement.setString(1, "%" + family + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            customers = this.extractResultSetCustomers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    public Integer addNewPerson(TblCustomer newCustomer) {
        Connection connection = connectionSql.getConnection();
        passwordGenerator = new PasswordGenerator();
        Integer result = null;
        try {

            if (this.findCustomerByNationalCode(newCustomer.getNationalCode(),"add") != null) {
                throw new SQLException("national code (username) is duplicate..!");
            }

            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into tblcustomer (name,family,nationalcode,mobilenumber,password) " +
                            " values (?,?,?,?,?) ");
            preparedStatement.setString(1, newCustomer.getName());
            preparedStatement.setString(2, newCustomer.getFamily());
            preparedStatement.setString(3, newCustomer.getNationalCode());
            preparedStatement.setString(4, newCustomer.getMobileNumber());
            preparedStatement.setString(5, passwordGenerator.hashPassword(newCustomer.getPassword()));

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public TblCustomer findCustomerByUserPass(String nationalCode, String password) {
        Connection connection = connectionSql.getConnection();
        passwordGenerator = new PasswordGenerator();
        customers = new ArrayList<>();
        TblCustomer findPerson = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from tblcustomer c where c.nationalcode=? and c.password=? ");
            preparedStatement.setString(1, nationalCode);
            preparedStatement.setString(2, passwordGenerator.hashPassword(password));

            ResultSet resultSet = preparedStatement.executeQuery();

            customers = this.extractResultSetCustomers(resultSet);

            if (customers != null && customers.size() > 1)
                throw new SQLException("there is two customer with same username and password");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!customers.isEmpty())
            findPerson = customers.get(0);

        return findPerson;
    }

    public TblCustomer findCustomerByNationalCode(String nationalCode ,String commandType) {
        Connection connection = connectionSql.getConnection();
        passwordGenerator = new PasswordGenerator();
        customers = new ArrayList<>();
        TblCustomer findPerson = null;


        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from tblcustomer c where c.nationalcode=? ");
            preparedStatement.setString(1, nationalCode);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (((ResultSetImpl) resultSet).getRows().isEmpty() && commandType.equalsIgnoreCase("find"))
                throw new SQLException(" customer national code (username) is not in the database");

            customers = this.extractResultSetCustomers(resultSet);

            if (customers != null && customers.size() > 1)
                throw new SQLException(" customer national code (username) is in the database");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!customers.isEmpty())
            findPerson = customers.get(0);

        return findPerson;
    }


    public TblCustomer findCustomerByCustomerNumber(int customerNumber) {
        Connection connection = connectionSql.getConnection();
        passwordGenerator = new PasswordGenerator();
        customers = new ArrayList<>();
        TblCustomer foundCustomer = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from tblcustomer c where c.id=? ");
            preparedStatement.setInt(1, customerNumber);

            ResultSet resultSet = preparedStatement.executeQuery();
            customers = this.extractResultSetCustomers(resultSet);
            if (customers == null)
                throw new SQLException(" customer number is not correct");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!customers.isEmpty())
            foundCustomer = customers.get(0);

        return foundCustomer;
    }


    private List<TblCustomer> extractResultSetCustomers(ResultSet resultSet) throws SQLException {
        customers = new ArrayList<>();
        while (resultSet.next()) {
            TblCustomer customer = new TblCustomer();
            customer.setId(resultSet.getInt(1));
            customer.setName(resultSet.getString(2));
            customer.setFamily(resultSet.getString(3));
            customer.setNationalCode(resultSet.getString(4));
            customer.setMobileNumber(resultSet.getString(5));
            customer.setPassword(resultSet.getString(6));
            customers.add(customer);
        }
        return customers;
    }

    public Integer updateCustomerInfo(TblCustomer updateCustomer) {

        Connection connection = connectionSql.getConnection();
        passwordGenerator = new PasswordGenerator();
        Integer result = null;
        try {

            if (this.findCustomerByNationalCode(updateCustomer.getNationalCode(),"find") == null) {
                throw new SQLException("customer national code is not entered");
            }

            PreparedStatement preparedStatement = connection
                    .prepareStatement("update tblcustomer  c set name=?,family=?,MobileNumber=?,password=?  where c.id=" + updateCustomer.getId());
            preparedStatement.setString(1, updateCustomer.getName());
            preparedStatement.setString(2, updateCustomer.getFamily());
            preparedStatement.setString(3, updateCustomer.getMobileNumber());
            preparedStatement.setString(4, updateCustomer.getPassword());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Integer deleteCustomer(TblCustomer deleteCustomer) {

        Connection connection = connectionSql.getConnection();
        Integer result = null;
        try {

            try (PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from tblcustomer  c   where c.id=?")) {
                preparedStatement.setInt(1,(deleteCustomer.getId()) );
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}

