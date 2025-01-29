package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

public class V1_1_1__create_student extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {
        try (Statement create = context.getConnection().createStatement()) {
            create.execute("""
                        CREATE TABLE student (
                            id BINARY(16) NOT NULL,
                            name VARCHAR(255) NOT NULL,
                            email VARCHAR(255) NOT NULL,
                            gender VARCHAR(255) NOT NULL,
                            PRIMARY KEY (id)
                        ) ENGINE=INNODB;
                    """);
        }
    }
}