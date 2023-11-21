package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    public PreparedStatement insertSt;
    public PreparedStatement readSt;
    public PreparedStatement setSt;
    public PreparedStatement deleteSt;
    public PreparedStatement getSt;
    public static final String INSERT ="INSERT INTO client VALUES(?,?)";
    public static final String READ_BY_ID ="SELECT* FROM client WHERE id = ?";
    public static final String SET_NAME = "UPDATE client SET name = ? WHERE id = ?";
    public static final String DELETE_CLIENT ="DELETE FROM client WHERE id=?";
    public static final String GET_ALL = "SELECT * FROM client";

    public ClientService(Connection connection) {
     try{
         insertSt = connection.prepareStatement(INSERT);
         readSt = connection.prepareStatement(READ_BY_ID);
         setSt = connection.prepareStatement(SET_NAME);
         deleteSt = connection.prepareStatement(DELETE_CLIENT);
         getSt = connection.prepareStatement(GET_ALL);
     }catch(SQLException e){
         System.out.println(e.getMessage());
     }
    }

    public long create(String name){
        long a = 0l;
        try{
            insertSt.setString(1, null);
            insertSt.setString(2, name);
            Database.getInstance().executeUpdate(insertSt);
            PreparedStatement last = Database.getConnection().prepareStatement("SELECT COUNT(*) FROM client");
            ResultSet resultSet = Database.getInstance().executeResult(last);
            a = resultSet.getLong("COUNT(*)");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return a;
    }

    public Client getById(long id){
        Client client = null;
        try{
            readSt.setLong(1,id);
            ResultSet resultSet = Database.getInstance().executeResult(readSt);
            if(resultSet.next()){
                client = new Client(resultSet.getLong("id"), resultSet.getString("NAME"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return client;
    }
    public void setName(long id, String name){
        try{
            setSt.setString(1, name);
            setSt.setLong(2, id);
            Database.getInstance().executeUpdate(setSt);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteById(long id){
        try{
            deleteSt.setLong(1, id);
            Database.getInstance().executeUpdate(deleteSt);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public List<Client> listAll(){
        List<Client> listOfCients = new ArrayList<>();
        try{
            ResultSet result = Database.getInstance().executeResult(getSt);
            while(result.next()){
                Client client = new Client(result.getLong("id"), result.getString("NAME"));
                listOfCients.add(client);
            }
        }catch(SQLException e){

        }
        return listOfCients;
    }

    public static void main(String[] args) {
        ClientService service = new ClientService(Database.getConnection());
        //System.out.println(service.create("Vasia Pupkin"));
       // System.out.println(service.getById(6).toString());
       // service.setName(6l, "Venera Miloska" );
        service.deleteById(5l);
//         service.listAll().forEach(a-> System.out.println(a.toString()));
    }
}
