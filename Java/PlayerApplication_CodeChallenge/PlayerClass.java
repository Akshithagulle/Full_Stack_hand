package pack.Java_application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerClass extends AbstractPlayer implements PlayerOperationsInterface {

    public PlayerClass(int id, String name, String skill, int exp, String country, double overallScore) {
        super(id, name, skill, exp, country, overallScore);
    }
    @Override
    public String toString() {
        return "Player Details: " +
               "\nID: " + id +
               "\nName: " + name +
               "\nSkill: " + skill +
               "\nExperience: " + exp + " years" +
               "\nCountry: " + country +
               "\nOverall Score: " + overallScore;
    }

    @Override
    public void addPlayer(PlayerClass player) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlayerDB", "root", "Akshitha@999")) {
            String query = "INSERT INTO players (id, name, skill, exp, country, overall_score) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, player.getId());
            stmt.setString(2, player.getName());
            stmt.setString(3, player.getSkill());
            stmt.setInt(4, player.getExp());
            stmt.setString(5, player.getCountry());
            stmt.setDouble(6, player.getOverallScore());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   

    @Override
    public PlayerClass getPlayerById(int id) {
        PlayerClass player = null;
        String query = "SELECT * FROM players WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlayerDB", "root", "Akshitha@999");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                player = new PlayerClass(
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getString("skill"),
                    rs.getInt("exp"), 
                    rs.getString("country"), 
                    rs.getDouble("overall_score")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching player by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return player;
    }


    @Override
    public List<PlayerClass> getPlayersBySkill(String skill) {
        List<PlayerClass> players = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlayerDB", "root", "Akshitha@999")) {
            String query = "SELECT * FROM players WHERE skill = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, skill);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                players.add(new PlayerClass(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
                        rs.getInt("exp"), rs.getString("country"), rs.getDouble("overall_score")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public List<PlayerClass> getPlayersByCountry(String country) {
        List<PlayerClass> players = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlayerDB", "root", "Akshitha@999")) {
            String query = "SELECT * FROM players WHERE country = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, country);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                players.add(new PlayerClass(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
                        rs.getInt("exp"), rs.getString("country"), rs.getDouble("overall_score")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public List<PlayerClass> getPlayersByExperience() {
        List<PlayerClass> players = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlayerDB", "root", "Akshitha@999")) {
            String query = "SELECT * FROM players ORDER BY exp DESC";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                players.add(new PlayerClass(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
                        rs.getInt("exp"), rs.getString("country"), rs.getDouble("overall_score")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public void updatePlayer(PlayerClass player) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlayerDB", "root", "Akshitha@999")) {
            String query = "UPDATE players SET name = ?, skill = ?, exp = ?, country = ?, overall_score = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getSkill());
            stmt.setInt(3, player.getExp());
            stmt.setString(4, player.getCountry());
            stmt.setDouble(5, player.getOverallScore());
            stmt.setInt(6, player.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlayer(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlayerDB", "root", "Akshitha@999")) {
            String query = "DELETE FROM players WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PlayerClass> getPlayersSortedBySkill() {
        List<PlayerClass> players = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlayerDB", "root", "Akshitha@999")) {
            String query = "SELECT * FROM players ORDER BY skill";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                players.add(new PlayerClass(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
                        rs.getInt("exp"), rs.getString("country"), rs.getDouble("overall_score")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<PlayerClass> getAllPlayers() {
        List<PlayerClass> players = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PlayerDB", "root", "Akshitha@999")) {
            String query = "SELECT * FROM players";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                players.add(new PlayerClass(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
                        rs.getInt("exp"), rs.getString("country"), rs.getDouble("overall_score")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
}
