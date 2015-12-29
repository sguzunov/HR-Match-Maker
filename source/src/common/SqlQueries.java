package common;

public class SqlQueries {

	// Users queries.
	public static final String CREATE_USER_SQL_QUERY = "INSERT INTO Users" + "(username,password,fk_usertype_id) "
			+ "VALUES(?,?,(SELECT usertype_id FROM usertypes WHERE usertype=?));";
	public static final String RETRIEVE_ALL_USERS_SQL_QUERY = "SELECT username,usertype " + "FROM users "
			+ "INNER JOIN usertypes ON " + "users.fk_usertype_id=usertype_id;";
	public static final String UPDATE_USER_PASSWORD_SQL_QUERY = "UPDATE users " + "SET password=? "
			+ "WHERE username=?;";

	// JobCVs queries.
	public static final String CREATE_JOBCV_SQL_QUERY = "INSERT INTO jobcvs"
			+ "(fk_user_id,fk_location_id,fk_education_id,fk_workposition_id,fk_worktype_id,requiredexperience,age) "
			+ "VALUES(" + "(SELECT user_id FROM users WHERE username=?),"
			+ "(SELECT location_id FROM locations WHERE city=? AND country=?),"
			+ "(SELECT education_id FROM educations WHERE education=?),"
			+ "(SELECT workposition_id FROM workpositions WHERE workposition=?),"
			+ "(SELECT worktype_id FROM worktypes WHERE worktype=?)," + "?,?" + ");";
	public static final String JOBCVS_CARRERFIELDS_CONNECTION_SQL_QUERY = "INSERT INTO jobcvs_carrerfields(fk_jobcv_id,fk_carrerfield_id) "
			+ "VALUES(?,?);";
	public static final String RETRIEVE_ALL_JOBCVS_SQL_QUERY = "SELECT jobcv_id,username,usertype,city,country,education,workposition,worktype,requiredexperience"
			+ "FROM jobcvs "
			+ "INNER JOIN users ON users.user_id=jobcvs.fk_users_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=fk_education_id "
			+ "INNER JOIN workpositions ON workpositions.workposition_id=fk_workposition_id "
			+ "INNER JOIN worktypes ON worktypes.worktype_id=fk_worktype_id;";
	public static final String RETRIEVE_ALL_CARRERFIELDS = "SELECT carrerfield FROM carrerfields "
			+ "INNER JOIN jobcvs_carrerfields ON carrerfields.carrerfield_id=jobcvs_carrerfields.fk_carrerfield_id "
			+ "INNER JOIN jobcvs ON ?=jobcvs_carrerfields.fk_jobcv_id;";
	public static final String RETRIEVE_ALL_LANGUAGES = "SELECT language FROM languages "
			+ "INNER JOIN jobcvs_languages ON languages.language_id=jobcvs_languages.fk_language_id "
			+ "INNER JOIN jobcvs ON ?=jobcvs_languages.fk_jobcv_id;";
	public static final String UPDATE_JOBCV_SQL_QUERY = "UPDATE jobcvs" + "inner join locations "
			+ "inner join educations " + "inner join workpositions " + "inner join worktypes " + "set "
			+ "jobcvs.fk_location_id=locations.location_id," + "jobcvs.fk_education_id=educations.education_id,"
			+ "jobcvs.fk_workposition_id=workpositions.workposition_id,"
			+ "jobcvs.fk_worktype_id=worktypes.worktype_id," + "jobcvs.requiredexperience=?," + "jobcvs.age=?"
			+ "where locations.city=? and locations.country=? and educations.education=?"
			+ "and workpositions.workposition=? and worktypes.worktype=?;";
	public static final String RETRIEVE_JOBCV_BY_ID_SQL_QUERY = "SELECT jobcv_id,username,usertype,city,country,education,workposition,worktype,requiredexperience"
			+ "FROM jobcvs "
			+ "INNER JOIN users ON users.user_id=jobcvs.fk_users_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=fk_education_id "
			+ "INNER JOIN workpositions ON workpositions.workposition_id=fk_workposition_id "
			+ "INNER JOIN worktypes ON worktypes.worktype_id=fk_worktype_id" + "WHERE jobcvs.jobcv_id=?;";

}
