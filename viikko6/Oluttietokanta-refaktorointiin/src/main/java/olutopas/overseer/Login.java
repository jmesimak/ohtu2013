package olutopas.overseer;

import java.util.Scanner;
import olutopas.Datamapper;
import olutopas.model.User;

public class Login implements Command {

    private Datamapper datamapper;
    private Scanner scanner;

    Login(Datamapper datamapper, Scanner scanner) {
        this.datamapper = datamapper;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("\nLogin (give ? to register a new user)\n");

            System.out.print("username: ");
            String name = scanner.nextLine();

            if (name.equals("?")) {
                registerUser();
                continue;
            }
            
            
            User user = this.datamapper.findUserByName(name);

            if (user != null) {
                this.datamapper.setCurrentUser(user);
                break;
            }
            System.out.println("unknown user");
        }
    }

    private void registerUser() {
        System.out.println("Register a new user");
        System.out.print("give username: ");
        String name = scanner.nextLine();
        User u = this.datamapper.findUserByName(name);
        if (u != null) {
            System.out.println("user already exists!");
            return;
        }
        this.datamapper.registerUser(new User(name));
        System.out.println("user created!\n");
    }
}
