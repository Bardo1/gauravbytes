package com.gauravbytes.springjdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.PreparedStatementCallback;

/**
 * 
 * @author Gaurav Rai Mazra
 *
 */
class GeneratedKeysPreparedStatementCallback implements PreparedStatementCallback<Integer> {

	@Override
	public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException {
		int updated = ps.executeUpdate();
		if (updated > 0) {
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next())
					return rs.getInt("id");
			}
			
			throw new DataRetrievalFailureException("There was no key auto generated by the database");
		}
		throw new DataRetrievalFailureException("Nothing was updated");
	}
}