package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public static String getConnectionURLForMySql(){
        try(InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream("application.properties")){
            Properties prop = new Properties();
            if(input == null){
                System.out.println("cannot find application.properties");
                return null;
            }

            prop.load(input);

            return new StringBuilder("jdbc:mysql://")
                    .append(prop.getProperty("mysql.db.host"))
                    .append(":")
                    .append(prop.getProperty("mysql.db.port"))
                    .append("/")
                    .append(prop.getProperty("mysql.db.database"))
                    .toString();

        }catch (IOException e){
              e.printStackTrace();
              return null;
        }
    }

    public static String getDatabaseUser(){
        try(InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream("application.properties")){
            Properties prop = new Properties();
            if(input == null){
                System.out.println("cannot find application.properties");
                return null;
            }

            prop.load(input);

            return prop.getProperty("mysql.db.username");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public static String getDatabasePassword(){
        try(InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream("application.properties")){
            Properties prop = new Properties();
            if(input == null){
                System.out.println("cannot find application.properties");
                return null;
            }

            prop.load(input);

            return prop.getProperty("mysql.db.password");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
