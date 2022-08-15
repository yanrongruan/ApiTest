package com.yiyibai;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class TestParameterXML {
    Connection con;

    @Test
    @Parameters({ "dbconfig", "poolsize" })
    public void createConnection(String dbconfig, int poolsize) {

        System.out.println("dbconfig : " + dbconfig);
        System.out.println("poolsize : " + poolsize);

        Properties prop = new Properties();
        InputStream input = null;

        try {
            // get properties file from project classpath
            String path = System.getProperty("user.dir")+"\\"+dbconfig;

            System.out.println("path => "+path);
            //input = getClass().getClassLoader().getResourceAsStream(path);

            //prop.load(input);
            prop.load(new FileInputStream(dbconfig));

            String drivers = prop.getProperty("jdbc.driver");
            String connectionURL = prop.getProperty("jdbc.url");
            String username = prop.getProperty("jdbc.username");
            String password = prop.getProperty("jdbc.password");

            System.out.println("drivers : " + drivers);
            System.out.println("connectionURL : " + connectionURL);
            System.out.println("username : " + username);
            System.out.println("password : " + password);

            Class.forName(drivers);
            con = DriverManager.getConnection(connectionURL, username, password);

        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        }

    }
}
