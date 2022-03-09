package fr.loirelique.lpsecurity;

import org.bukkit.Bukkit;

import java.sql.*;
import java.util.UUID;

public class Liaison {

    private String driver;
    private String host;
    private String port;
    private String data;
    private String username;
    private String password;
    private static Connection connection;

    public Liaison(String driver, String host,String port, String data, String username, String password){
        this.driver = driver;
        this.host = host;
        this.port = port;
        this.data = data;
        this.username = username;
        this.password = password;
    }

    /*
     * CONNEXION A LA BASE DE DONNEE
     */

    public static Connection getConnection() {
        return connection;
    }
    public void connect(){
        if(!isOnline()){
            try{
                String url = this.driver+"://"+this.host+":"+this.port+"/"+this.data+"?characterEncoding=latin1&useConfigs=maxPerformance";
                String user = this.username;
                String pass = this.password;
                connection = DriverManager.getConnection(url,user,pass);
               
                                                    
                System.out.println("§aConnexion réussie !");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /*
    * AJOUTER LE COMPTE
     */

    public void addAccount(UUID uuid){
        if(!isAccount(uuid)){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO players (pseudo, uuid_player, ip_player) VALUES (?, ?, ?)");
                preparedStatement.setString(1, Bukkit.getPlayer(uuid).getName());
                preparedStatement.setString(2, uuid.toString());
                preparedStatement.setString(3, Bukkit.getPlayer(uuid).getAddress().getHostName());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /*
    * VERIFIER S'IL POSSEDE DEJA UN COMPTE
     */

    public boolean isAccount(UUID uuid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT pseudo FROM players WHERE uuid = ?");
            preparedStatement.setString(1, Bukkit.getPlayer(uuid).getName());
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.setString(3, Bukkit.getPlayer(uuid).getAddress().getHostName());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    * DECONNEXION DE LA BASE DE DONNEE
     */

    public void disconnect(){
        if(isOnline()){
            try {
                connection.close();
                System.out.println("§cDéconnexion réussie !");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * VERIFICATION DE LA CONNEXION DE LA BASE DE DONNEE
     */

    public boolean isOnline(){
        try {
            if((connection == null) || (connection.isClosed())){
                return false;
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
