package emsi.ssii.devoir.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import emsi.ssii.devoir.models.Admin;
import emsi.ssii.devoir.models.Client;
import emsi.ssii.devoir.models.Dev;
import emsi.ssii.devoir.repo.UserRepo;

@Component
public class DBSeeder implements CommandLineRunner {

    @Autowired
    private UserRepo<Admin> aRepo;
    @Autowired
    private UserRepo<Dev> dRepo;
    @Autowired
    private UserRepo<Client> cRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (aRepo.findAll().isEmpty()) {
            Admin admin = new Admin();
            admin.setDisplayName("admin");
            admin.setEmail("admin@mail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Constants.getRole("admin"));
            aRepo.save(admin);

            Client client = new Client();
            client.setDisplayName("nejm");
            client.setEmail("nejm@mail.com");
            client.setPassword(passwordEncoder.encode("nejm"));
            client.setRole(Constants.getRole("client"));
            cRepo.save(client);
        
            Dev dev = new Dev();
            dev.setDisplayName("ahid");
            dev.setEmail("ahid@mail.com");
            dev.setPassword(passwordEncoder.encode("ahid"));
            dev.setRole(Constants.getRole("dev"));
            dRepo.save(dev);
        }
    }

}

