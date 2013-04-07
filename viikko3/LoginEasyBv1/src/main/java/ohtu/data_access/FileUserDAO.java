package ohtu.data_access;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileUserDAO implements UserDao {

    private String pathToFile;
    private List<User> users;
    
    public FileUserDAO(String path) {
        
        this.pathToFile = path;
        this.users = new ArrayList<User>();
        
    }

    @Override
    public List<User> listAll() {
        try {
            writeUsersToMemory();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.users;
    }

    @Override
    public User findByName(String name) {
        listAll();
        for (User u : this.users) {
            if (u.getUsername().equals(name)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        this.users.add(user);
        try {
            writeUsersToFile();
        } catch (IOException ex) {
            System.out.println("Error writing to file");
        }
    }
    
    private void writeUsersToFile() throws IOException {
        FileWriter scribe = new FileWriter (new File(this.pathToFile));
        for (User u : this.users) {
            scribe.write(u.getUsername()+","+u.getPassword()+"\n");
        }
        scribe.close();
    }
    
    private void writeUsersToMemory() throws IOException {
        Scanner lukija = new Scanner(new File(this.pathToFile));
        this.users.clear();
        while (lukija.hasNextLine()) {
            String user = lukija.nextLine();
            String[] userArray = user.split(",");
            this.users.add(new User(userArray[0], userArray[1]));
        }
        lukija.close();
    }
}
