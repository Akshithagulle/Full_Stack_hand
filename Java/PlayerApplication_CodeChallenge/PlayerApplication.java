package pack.Java_application;

import java.util.List;
import java.util.Scanner;

public class PlayerApplication {

    static final Scanner scanner = new Scanner(System.in);
    static final PlayerClass playerOperations = new PlayerClass(0, "", "", 0, "", 0);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addPlayer();
                case 2 -> fetchPlayerById();
                case 3 -> listPlayersBySkill();
                case 4 -> listPlayersByCountry();
                case 5 -> listPlayersByExperience();
                case 6 -> updatePlayer();
                case 7 -> deletePlayer();
                case 8->  sortPlayersBySkill();
                case 9 -> {
                    System.out.println("Exiting application.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void fetchPlayerById() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();

        PlayerClass player = playerOperations.getPlayerById(id);
        if (player == null) {
            System.out.println("No player found with ID " + id);
        } else {
            System.out.println("Player details of ID " + id + ":");
            System.out.println(player);
        }
    }


	private static void showMenu() {
        System.out.println("\n=== Player Management System ===");
        System.out.println("1. Add Player");
        System.out.println("2. Fetch Player by ID");
        System.out.println("3. List Players by Skill");
        System.out.println("4. List Players by Country");
        System.out.println("5. List Players by Experience");
        System.out.println("6. Update Player");
        System.out.println("7. Delete Player");
        System.out.println("8. sorted Player by skills");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addPlayer() {
        System.out.print("Enter Player ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Player Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Skill: ");
        String skill = scanner.nextLine();

        System.out.print("Enter Experience (years): ");
        int exp = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Country: ");
        String country = scanner.nextLine();

        System.out.print("Enter Overall Score: ");
        double score = scanner.nextDouble();

        PlayerClass newPlayer = new PlayerClass(id, name, skill, exp, country, score);
        playerOperations.addPlayer(newPlayer);
        System.out.println("Player added successfully!");
    }

    
    private static void listPlayersBySkill() {
        System.out.print("Enter Skill: ");
        String skill = scanner.nextLine();
        List<PlayerClass> players = playerOperations.getPlayersBySkill(skill);
        if (players.isEmpty()) {
            System.out.println("No players with the specified skill.");
        } else {
            System.out.println("Players with skill " + skill + ":");
            players.forEach(System.out::println);
        }
    }

    private static void listPlayersByCountry() {
        System.out.print("Enter Country: ");
        String country = scanner.nextLine();
        List<PlayerClass> players = playerOperations.getPlayersByCountry(country);
        if (players.isEmpty()) {
            System.out.println("No players from " + country + ".");
        } else {
            System.out.println("Players from " + country + ":");
            players.forEach(System.out::println);
        }
    }

    private static void listPlayersByExperience() {
        List<PlayerClass> players = playerOperations.getPlayersByExperience();
        if (players.isEmpty()) {
            System.out.println("No players found.");
        } else {
            System.out.println("Players sorted by experience:");
            players.forEach(System.out::println);
        }
    }
 // List players sorted by skill
    public static void sortPlayersBySkill() {
        List<PlayerClass> sortedPlayers = playerOperations.getPlayersSortedBySkill();
        if (sortedPlayers.isEmpty()) {
            System.out.println("No players found.");
        } else {
            System.out.println("Players sorted by skill:");
            for (PlayerClass p : sortedPlayers) {
                System.out.println("Name: " + p.getName() + " | Skill: " + p.getSkill());
            }
        }
    }

    private static void updatePlayer() {
        System.out.print("Enter Player ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        PlayerClass existingPlayer = playerOperations.getPlayerById(id);
        if (existingPlayer != null) {
            System.out.print("Enter New Skill: ");
            String skill = scanner.nextLine();

            System.out.print("Enter New Experience: ");
            int exp = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter New Country: ");
            String country = scanner.nextLine();

            System.out.print("Enter New Overall Score: ");
            double score = scanner.nextDouble();

            existingPlayer.setSkill(skill);
            existingPlayer.setExp(exp);
            existingPlayer.setCountry(country);
            existingPlayer.setOverallScore(score);
            playerOperations.updatePlayer(existingPlayer);
            System.out.println("Player updated successfully!");
        } else {
            System.out.println("Player not found.");
        }
    }

    private static void deletePlayer() {
        System.out.print("Enter Player ID to delete: ");
        int id = scanner.nextInt();
        playerOperations.deletePlayer(id);
        System.out.println("Player deleted successfully!");
    }
}  
