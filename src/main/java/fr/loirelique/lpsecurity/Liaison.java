package fr.loirelique.lpsecurity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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

    public Liaison(String driver, String host, String port, String data, String username, String password) {
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

    public void connect() {
        if (!isOnline()) {
            try {
                String url = this.driver + "://" + this.host + ":" + this.port + "/" + this.data
                        + "?characterEncoding=latin1&useConfigs=maxPerformance";
                String user = this.username;
                String pass = this.password;
                connection = DriverManager.getConnection(url, user, pass);

                System.out.println("§aConnexion réussie !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * AJOUTER LE COMPTE
     */

    public void addAccount(UUID uuid) {
        if (!isAccount(uuid)) {
            try {
                PreparedStatement preparedStatement = connection
                        .prepareStatement("INSERT INTO players (pseudo, uuid_player, ip_player) VALUES (?, ?, ?)");
                preparedStatement.setString(1, Bukkit.getPlayer(uuid).getName());
                preparedStatement.setString(2, uuid.toString());
                preparedStatement.setString(3, Bukkit.getPlayer(uuid).getAddress().getHostName());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * VERIFIER S'IL POSSEDE DEJA UN COMPTE
     */

    public boolean isAccount(UUID uuid) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT pseudo FROM players WHERE uuid = ?");
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

    public void disconnect() {
        if (isOnline()) {
            try {
                connection.close();
                System.out.println("§cDéconnexion réussie !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * VERIFICATION DE LA CONNEXION DE LA BASE DE DONNEE
     */

    public boolean isOnline() {
        try {
            if ((connection == null) || (connection.isClosed())) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * VERIFICATION SI LE JOUEUR ET DEJA CONNECTER
     */

    public boolean playerIsOnline(UUID uuid){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT pseudo FROM players WHERE uuid = ?");
            preparedStatement.setString(1, Bukkit.getPlayer(uuid).getName());
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.setString(3, Bukkit.getPlayer(uuid).getAddress().getHostName());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        return false;
    }

    /*
     * //Try ressource car quand le block sera fini on ferme la connection
     * automatiquement
     * try(Connection ouverture_connection = DriverManager.getConnection(url, user,
     * password))
     * {
     * 
     * // Mettre à jour bdd sans PrepareStatement
     * String requet_sql1 = "INSERT INTO EX_emple (id, login, pass)" +
     * "VALUES(1,'lui','rednez')";
     * try (Statement statement1 = ouverture_connection.createStatement()) {
     * // Pour Update
     * statement1.executeUpdate(requet_sql1);
     * }
     * 
     * // Faire une selection de données dans la bdd sans PrepareStatement
     * String requet_sql2 = "SELECT * FROM EX_emple";
     * try (Statement statement2 = ouverture_connection.createStatement()) {
     * // Pour faire une requete try ressource pour auto close ResultSet
     * try (ResultSet resultat_requete = statement2.executeQuery(requet_sql2)) {
     * while (resultat_requete.next()) {
     * int result_id = resultat_requete.getInt(1);
     * String result_login = resultat_requete.getString("login");
     * String result_pass = resultat_requete.getString(3);
     * 
     * System.out.println(result_id + result_login + result_pass);
     * }
     * }
     * }
     * 
     * }
     */

    //-1 on ouvre la connection

    public void test(Player pevent) throws SQLException{
    String url = this.driver + "://" + this.host + ":" + this.port + "/" + this.data
                        + "?characterEncoding=latin1&useConfigs=maxPerformance";
                String user = this.username;
                String pass = this.password;
    try( Connection ouverture_connection = DriverManager.getConnection(url, user, pass))
    {

        //-2 Fait une ou plusieure requete connection au jeux
        
        String requet_Select_sql2 = "SELECT * FROM Table_user WHERE uuid=?";
        try (PreparedStatement statement2_select = ouverture_connection.prepareStatement(requet_Select_sql2)) {
            UUID uuid_player = pevent.getUniqueId() ;
            statement2_select.setObject(1,uuid_player);
            // 
            try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                if(resultat_requete_select.next()){
                    String requet_Update_sql3 ="UPDATE Table_user SET online=1 WHERE uuid=?";
                    try (PreparedStatement statement3_update = ouverture_connection.prepareStatement(requet_Update_sql3)) {
                        statement3_update.setObject(1,resultat_requete_select.getObject("uuid"));
                        statement3_update.executeUpdate();
                    }
                }
                System.out.println("Pas d'uuid correspondant");

            }
        }

    }
 }

}
