package persistence;

import java.sql.SQLException;
import java.util.Collection;

import common.EnumUtils;
import enums.CarrerField;
import helpers.DatabaseHelper;
import models.JobCV;

public class JobCvDao extends JobAccountDao {
	private static final String CREATE_JOBCV_SQL_QUERY = "INSERT INTO jobcvs"
			+ "(fk_user_id,fk_location_id,fk_education_id,fk_workposition_id,fk_worktype_id,requiredexperience,age) "
			+ "VALUES(" + "(SELECT user_id FROM users WHERE username=?),"
			+ "(SELECT location_id FROM locations WHERE city=? AND country=?),"
			+ "(SELECT education_id FROM educations WHERE education=?),"
			+ "(SELECT workposition_id FROM workpositions WHERE workposition=?),"
			+ "(SELECT worktype_id FROM worktypes WHERE worktype=?)," + "?,?" + ");";

	public JobCvDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public <E> E getById(int id) {
		return null;
	}

	@Override
	public <E> void create(E data) {
		JobCV newJobCV = (JobCV) data;
		try {
			super.connection = super.dataSource.getConnection();
			super.preparedStatement = connection.prepareStatement(CREATE_JOBCV_SQL_QUERY);

			// Database requires strings for values.
			String educationAsString = EnumUtils.ConvertEnumValueToString(newJobCV.getRequiredEducation());
			String workPositionAsString = EnumUtils.ConvertEnumValueToString(newJobCV.getWorkPostion());
			String workTypeAsString = EnumUtils.ConvertEnumValueToString(newJobCV.getWorkType());
			super.preparedStatement.setString(1, newJobCV.getCreatedBy().getUserName());
			super.preparedStatement.setString(2, newJobCV.getLocation().getCity());
			super.preparedStatement.setString(3, newJobCV.getLocation().getCountry());
			super.preparedStatement.setString(4, educationAsString);
			super.preparedStatement.setString(5, workPositionAsString);
			super.preparedStatement.setString(6, workTypeAsString);
			super.preparedStatement.setBoolean(7, newJobCV.isRequiredExperience());
			super.preparedStatement.setInt(8, newJobCV.getAge());

			// Add required location to the database.
			DatabaseHelper.insertLocationIntoDatabase(super.connection, newJobCV.getLocation().getCity(),
					newJobCV.getLocation().getCountry());

			// Add required education to the database.
			DatabaseHelper.insertEducationIntoDatabase(super.connection, educationAsString);

			// Add required work position to the database.
			DatabaseHelper.insertWorkPositionIntoDatabase(super.connection, workPositionAsString);

			// Add required work type to the database.
			DatabaseHelper.insertWorkTypeIntoDatabase(super.connection, workTypeAsString);
			super.preparedStatement.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (super.connection != null) {
				super.closeConnection();
			}
			if (super.preparedStatement != null) {
				super.closePreparedStatement();
			}
		}
	}

	@Override
	public <E> Collection<E> retrieve() throws ClassNotFoundException, SQLException {
		return null;
	}

	@Override
	public <E> void update(E data) {

	}

	private void establishConnectionWithCarrerFields(Collection<CarrerField> carrerFields, int jobcv_last_id) {
		for (CarrerField carrerField : carrerFields) {
			String carrerFieldAsString = EnumUtils.ConvertEnumValueToString(carrerField);
			// TODO: Complete tables connection.
		}
	}

}
