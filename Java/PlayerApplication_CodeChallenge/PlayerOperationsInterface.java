package pack.Java_application;

import java.util.List;

public interface PlayerOperationsInterface {
    void addPlayer(PlayerClass player);
    PlayerClass getPlayerById(int id);
    List<PlayerClass> getPlayersBySkill(String skill);
    List<PlayerClass> getPlayersByCountry(String country);
    List<PlayerClass> getPlayersByExperience();
    void updatePlayer(PlayerClass player);
    void deletePlayer(int id);
}
