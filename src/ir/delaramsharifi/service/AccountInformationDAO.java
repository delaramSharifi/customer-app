package ir.delaramsharifi.service;

import com.mysql.cj.MysqlType;
import ir.delaramsharifi.entity.TblAccountInformation;
import ir.delaramsharifi.entity.TblCustomer;
import ir.delaramsharifi.utility.PasswordGenerator;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AccountInformationDAO {

    ConnectionSql connectionSql = new ConnectionSql();
    List<TblAccountInformation> accountInformations;

    public List<TblAccountInformation> getAllAccountInformation() {
        Connection connection = connectionSql.getConnection();
        accountInformations = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from tblaccountinformation");
            accountInformations = extractResultSetAccountInformations(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountInformations;
    }


    public Integer addAccountInformation(TblAccountInformation accountInformation) {
        Connection connection = connectionSql.getConnection();
        Integer result = null;
        try {


/*            if (this.findAccountInformationByCustomerIdAndAccountTypeId(accountInformation.getCustomerId(),accountInformation.getAccountTypeId()) != null) {
                throw new SQLException("customer has that account in the database..!");
            }*/

            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into tblaccountinformation (customerid,accounttypeid,cashamount,withdrewAmount,depositAmount,withdrewDate,depositDate,comment) " +
                            " values (?,?,?,?,?,?,?,?) ");
            preparedStatement.setLong(1, accountInformation.getCustomerId());
            preparedStatement.setLong(2, accountInformation.getAccountTypeId());
            preparedStatement.setLong(3, accountInformation.getCashAmount());

            if(accountInformation.getWithdrewAmount()==null){
                preparedStatement.setNull(4, MysqlType.INT.getJdbcType());
                preparedStatement.setNull(6, MysqlType.TIMESTAMP.getJdbcType());
            }else{
                preparedStatement.setInt(4, accountInformation.getWithdrewAmount());
                preparedStatement.setTimestamp(6, accountInformation.getWithdrewDate());
            }
//            preparedStatement.setNull(6, MysqlType.DATETIME.getJdbcType());


            if(accountInformation.getDepositAmount()==null){
                preparedStatement.setNull(5, MysqlType.INT.getJdbcType());
                preparedStatement.setNull(7, MysqlType.TIMESTAMP.getJdbcType());
            }else{
                preparedStatement.setInt(5, accountInformation.getDepositAmount());
                preparedStatement.setTimestamp(7, accountInformation.getDepositDate());
            }

            preparedStatement.setString(8, accountInformation.getComment());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<TblAccountInformation> findAccountInformationByCustomerId(int customerNumber) {
        Connection connection = connectionSql.getConnection();
        accountInformations = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from tblaccountinformation ai where ai.customerid=?");
            preparedStatement.setInt(1, customerNumber);

            ResultSet resultSet = preparedStatement.executeQuery();
            accountInformations = extractResultSetAccountInformations(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountInformations;
    }




    private Timestamp convertJavaDateToSqlDate(java.util.Date javaDate) {
        if (javaDate == null)
            return null;
        return new Timestamp(javaDate.getTime());
    }

    private List<TblAccountInformation> extractResultSetAccountInformations(ResultSet resultSet) throws SQLException {
        accountInformations = new ArrayList<>();
        while (resultSet.next()) {
            TblAccountInformation accountInformation = new TblAccountInformation();
            accountInformation.setId(resultSet.getInt(1));
            accountInformation.setCustomerId(resultSet.getInt(2));
            accountInformation.setAccountTypeId(resultSet.getInt(3));
            accountInformation.setCashAmount(resultSet.getInt(4));
            accountInformation.setWithdrewAmount(resultSet.getInt(5));
            accountInformation.setDepositAmount(resultSet.getInt(6));
            accountInformation.setWithdrewDate(resultSet.getTimestamp(7));
            accountInformation.setDepositDate(resultSet.getTimestamp(8));
            accountInformation.setComment(resultSet.getString(9));
            accountInformations.add(accountInformation);
        }
        return accountInformations;
    }
}
