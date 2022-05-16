package core.db.tables.customer;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class CustomerEntity {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String serNo;

    public static class CustomerEntityMapper {
        public static CustomerEntity mapRow(ResultSet rs) throws SQLException {
            CustomerEntity customerEntity = new CustomerEntity();
            if (rs.getRow() != 0) {
                customerEntity.setId(rs.getInt("id"));
                customerEntity.setFirstName(rs.getString("first_name"));
                customerEntity.setLastName(rs.getString("last_name"));
                customerEntity.setEmail(rs.getString("email"));
                customerEntity.setSerNo(rs.getString("ser_no"));
            }
            return customerEntity;
        }
    }
}