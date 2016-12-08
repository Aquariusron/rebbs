package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Branch;
import dao.BranchDao;

public class BranchService {

	public List<Branch> getBranches() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Branch> ret = new BranchDao().getBranches(connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
