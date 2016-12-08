package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Position;
import dao.PositionDao;

public class PositionService {

	public List<Position> getPositions() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Position> ret = new PositionDao().getPositions(connection);

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
