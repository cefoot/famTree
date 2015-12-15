package hello;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Server {

    @Autowired
    private PersonRepository repo;

    @RequestMapping("/data")
    String loadPersons() {
        StringBuilder buffer = new StringBuilder();
        repo.findAll().forEach(p -> appendToBuffer(buffer, p));
        return buffer.toString();
    }

    @RequestMapping(value = "/data", method = RequestMethod.PUT, consumes = "text/plain")
    void savePerson(String name) {
        Person p = new Person();
        p.setLastName(name);
        p.setFirstName("Rofl");
        repo.save(p);
    }

    private void appendToBuffer(StringBuilder buffer, Person p) {
        buffer.append(p);
        buffer.append(";");
    }
}
