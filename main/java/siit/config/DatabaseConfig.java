package siit.config;

import org.h2.jdbcx.JdbcDataSource;
import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DatabaseConfig {
    private static DatabaseConfig instance = null;

    PGSimpleDataSource dataSource;

    private DatabaseConfig() {
        this.dataSource = getDataSourceConfig();
    }

    public static PGSimpleDataSource getInstance(){
        if(instance == null){
            instance = new DatabaseConfig();
        }
        return instance.dataSource;
    }


    private PGSimpleDataSource getDataSourceConfig() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String[] hosts = {"localhost"};
        dataSource.setServerNames(hosts);
        dataSource.setDatabaseName( "sci" );
        dataSource.setUser( "postgres" );
        dataSource.setPassword( "postgres" );

        return dataSource;
    }
}
