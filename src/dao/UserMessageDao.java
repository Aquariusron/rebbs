package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import beans.UserMessage;
import exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getCategories(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT category FROM messages ");
			sql.append("GROUP BY category ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toCategoriesList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<UserMessage> toCategoriesList(ResultSet rs)
			throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				String category = rs.getString("category");

				UserMessage categories = new UserMessage();
				categories.setCategory(category);
				ret.add(categories);
			}

			return ret;
		} finally {
			close(rs);
		}
	}

	public UserMessage getNewDate(Connection connection){
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_message ORDER BY insert_dt DESC limit 1");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret =  toUserMessageList(rs);
			UserMessage current;
			return current =ret.get(0);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public UserMessage getOldDate(Connection connection){
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_message ORDER BY insert_dt ASC limit 1");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret =  toUserMessageList(rs);
			UserMessage old;
			return old =ret.get(0);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<UserMessage> getUserMessages(Connection connection, String category, String current, String old, int num) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT * FROM user_message where insert_dt between ? and ? ");

			if(!StringUtils.isEmpty(category)){
				sql.append("and category = ?");
			}
			sql.append("ORDER BY insert_dt DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, old);
			ps.setString(2, current + " 23:59:59");

			if(!StringUtils.isEmpty(category)){
				ps.setString(3, category);
			}

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);
			return ret;
			} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}



	private List<UserMessage> toUserMessageList(ResultSet rs)
			throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp insertDate = rs.getTimestamp("insert_dt");
				String name = rs.getString("name");
				int id = rs.getInt("id");

				UserMessage message = new UserMessage();
				message.setSubject(subject);
				message.setText(text);
				message.setCategory(category);
				message.setDate(insertDate);
				message.setName(name);
				message.setId(id);

				ret.add(message);
			}

			return ret;
		} finally {
			close(rs);
		}
	}

	public List<UserMessage> getUserComments(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_comment ");
			sql.append("ORDER BY insert_dt DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<UserMessage> toUserCommentList(ResultSet rs)
			throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				String text = rs.getString("text");
				Timestamp insertDate = rs.getTimestamp("insert_dt");
				String name = rs.getString("name");

				UserMessage message = new UserMessage();
				message.setText(text);
				message.setDate(insertDate);
				message.setName(name);

				ret.add(message);
			}

			return ret;
		} finally {
			close(rs);
		}
	}
}
