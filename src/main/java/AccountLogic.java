import java.sql.SQLException;
import java.util.List;

public interface AccountLogic {
    void add() throws SQLException;

    void delete();

    Account getAccount(int id);

    List<Account> getAllAccounts();
}
