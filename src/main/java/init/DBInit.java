package init;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Tomas Perez Molina
 */
public class DBInit {

    public static void main(String[] args) throws Exception{
        System.out.println("In main");
        if(args.length < 2)
            throw new Exception("Not enough arguments, database path and name are needed");
        init(args[0], args[1]);
    }

    public static void init(String path, String name) throws Exception {
        try {
            System.out.println("Starting Database");
            HsqlProperties p = new HsqlProperties();
            p.setProperty("server.database.0", "file:" + path);
            p.setProperty("server.dbname.0", name);
            Server server = new Server();
            server.setProperties(p);
            server.setLogWriter(new PrintWriter(System.out));
            server.setErrWriter(new PrintWriter(System.out));
            server.start();
        } catch (ServerAcl.AclFormatException | IOException afex) {
            throw new Exception(afex);
        }
    }
}
