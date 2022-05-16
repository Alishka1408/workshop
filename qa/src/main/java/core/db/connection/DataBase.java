package core.db.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DataBase {
    FRAUD("jdbc:postgresql://localhost:5432/fraud", "workshop", "password"),
    CUSTOMER("jdbc:postgresql://localhost:5432/customer","workshop", "password");
    @Getter
    private final String dbUrl;
    @Getter
    private String user;
    @Getter
    private String password;
}