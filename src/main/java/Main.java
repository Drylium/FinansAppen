import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        checkForEnvs();
        Application application = new Application();
        application.run();
    }

    public static void checkForEnvs() {
        String dbUrl = System.getenv("DATABASE_URL");
        String dbUser = System.getenv("DATABASE_USER");
        String dbPassword = System.getenv("DATABASE_PASSWORD");
        boolean missingEnvVars = false;
        if (dbUrl == null || dbUrl.isBlank()) {
            System.out.println("Missing env var 'DATABASE_URL'");
            missingEnvVars = true;
        }


        if (dbUser == null || dbUser.isBlank()) {
            System.out.println("Missing env var 'DATABASE_USER'");
            missingEnvVars = true;
        }


        if (dbPassword == null || dbPassword.isBlank()) {
            System.out.println("Missing env var 'DATABASE_PASSWORD'");
            missingEnvVars = true;
        }

        if (missingEnvVars) {
            System.exit(0);
        }
    }
}

