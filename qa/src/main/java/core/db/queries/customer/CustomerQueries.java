package core.db.queries.customer;

import core.db.connection.DataBase;
import core.db.executor.QueriesExecutor;
import core.db.tables.customer.CustomerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerQueries {

    private static final QueriesExecutor queriesExecutor = new QueriesExecutor(DataBase.CUSTOMER);

    public static List<CustomerEntity> getCustomers() throws SQLException {
        List<CustomerEntity> customerEntities = new ArrayList<>();
        ResultSet result = queriesExecutor
                .executeQuery("select * from customer");

        while (result.next()) {
            customerEntities.add(CustomerEntity.CustomerEntityMapper.mapRow(result));
        }
        return customerEntities;
    }

    public static List<CustomerEntity> getCustomerById(int id) throws SQLException {
        List<CustomerEntity> customerEntities = new ArrayList<>();
        ResultSet result = queriesExecutor
                .executeQuery(String.format("select * from customer where id in ('%s')", id));

        while (result.next()) {
            customerEntities.add(CustomerEntity.CustomerEntityMapper.mapRow(result));
        }
        return customerEntities;
    }
}