package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.Comment;
import exception.SQLRuntimeException;
public class CommentDao {
	public List<Comment> getComments(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_comment ");
			sql.append("ORDER BY insert_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Comment> ret = toCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Comment> toCommentList(ResultSet rs)
			throws SQLException {

		List<Comment> ret = new ArrayList<Comment>();
		try {
			while (rs.next()) {
				String text = rs.getString("text");
				String messageId = rs.getString("message_id");
				Timestamp insertDate = rs.getTimestamp("insert_at");
				String name = rs.getString("name");

				Comment comment = new Comment();
				comment.setMessageId(Integer.parseInt(messageId));
				comment.setComment(text);
				comment.setInsertDate(insertDate);
				comment.setName(name);

				ret.add(comment);
			}

			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");

			sql.append("text");
			sql.append(", user_id");
			sql.append(", message_id");
			sql.append(", insert_dt");
			sql.append(", update_dt");
			sql.append(") VALUES (");
			sql.append(" ?"); // text
			sql.append(", ?");// message_id
			sql.append(", ?");// user_id
			sql.append(", CURRENT_TIMESTAMP"); // insert_dt
			sql.append(", CURRENT_TIMESTAMP"); // update_dt
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getComment());
			ps.setInt(2, comment.getUserId());
			ps.setInt(3, comment.getMessageId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
