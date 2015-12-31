package common;

public class SqlQueries {

	// Persistence helper queries.
	public static final String INSERT_LOCATION_SQL_QUERY = "INSERT INTO " + "locations(city,country) " + "VALUES(?,?) "
			+ "ON DUPLICATE KEY UPDATE city=city,country=country;";

	public static final String INSERT_USERTYPE_SQL_QUERY = "INSERT INTO " + "usertypes(usertype) " + "VALUES(?) "
			+ "ON DUPLICATE KEY UPDATE usertype=usertype;";

	public static final String INSERT_EDUCATION_SQL_QUERY = "INSERT INTO " + "educations(education) " + "VALUES(?) "
			+ "ON DUPLICATE KEY UPDATE education=education;";

	public static final String INSERT_WORKPOSITION_SQL_QUERY = "INSERT INTO " + "workpositions(workposition) "
			+ "VALUES(?) " + "ON DUPLICATE KEY UPDATE workposition=workposition;";

	public static final String INSERT_WORKTYPE_SQL_QUERY = "INSERT INTO " + "worktypes(worktype) " + "VALUES(?) "
			+ "ON DUPLICATE KEY UPDATE worktype=worktype;";

	public static final String INSERT_CARRERFIELD_SQL_QUERY = "INSERT INTO " + "carrerfields(carrerfield) "
			+ "VALUES(?) " + "ON DUPLICATE KEY UPDATE carrerfield=carrerfield;";

	public static final String INSERT_LANGUAGE_SQL_QUERY = "INSERT INTO " + "languages(language) " + "VALUES(?) "
			+ "ON DUPLICATE KEY UPDATE language=language;";

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

	public static final String JOBCVS_LANGUAGES_CONNECTION_SQL_QUERY = "INSERT INTO jobcvs_languages(fk_jobcv_id,fk_language_id) "
			+ "VALUES(?,?);";

	public static final String RETRIEVE_ALL_JOBCVS_SQL_QUERY = "SELECT jobcv_id,username,usertype,city,country,education,workposition,worktype,requiredexperience"
			+ "FROM jobcvs "
			+ "INNER JOIN users ON users.user_id=jobcvs.fk_users_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=fk_education_id "
			+ "INNER JOIN workpositions ON workpositions.workposition_id=fk_workposition_id "
			+ "INNER JOIN worktypes ON worktypes.worktype_id=fk_worktype_id;";

	public static final String RETRIEVE_ALL_CARRERFIELDS_BY_JOBCVS_SQL_QUERY = "SELECT carrerfield FROM carrerfields "
			+ "INNER JOIN jobcvs_carrerfields ON carrerfields.carrerfield_id=jobcvs_carrerfields.fk_carrerfield_id "
			+ "INNER JOIN jobcvs ON ?=jobcvs_carrerfields.fk_jobcv_id;";

	public static final String RETRIEVE_ALL_LANGUAGES_SQL_QUERY = "SELECT language FROM languages "
			+ "INNER JOIN jobcvs_languages ON languages.language_id=jobcvs_languages.fk_language_id "
			+ "INNER JOIN jobcvs ON ?=jobcvs_languages.fk_jobcv_id;";

	public static final String UPDATE_JOBCV_SQL_QUERY = "UPDATE jobcvs" + "inner join locations "
			+ "inner join educations " + "inner join workpositions " + "inner join worktypes " + "set "
			+ "jobcvs.fk_location_id=locations.location_id," + "jobcvs.fk_education_id=educations.education_id,"
			+ "jobcvs.fk_workposition_id=workpositions.workposition_id,"
			+ "jobcvs.fk_worktype_id=worktypes.worktype_id," + "jobcvs.requiredexperience=?," + "jobcvs.age=?"
			+ "where locations.city=? and locations.country=? and educations.education=?"
			+ "and workpositions.workposition=? and worktypes.worktype=?;";

	public static final String RETRIEVE_JOBCV_BY_ID_SQL_QUERY = "SELECT jobcv_id,username,usertype,city,country,education,workposition,worktype,requiredexperience,age"
			+ "FROM jobcvs "
			+ "INNER JOIN users ON users.user_id=jobcvs.fk_users_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=fk_education_id "
			+ "INNER JOIN workpositions ON workpositions.workposition_id=fk_workposition_id "
			+ "INNER JOIN worktypes ON worktypes.worktype_id=fk_worktype_id" + "WHERE jobcvs.jobcv_id=?;";

	// Job advertisement queries.
	public static final String CREATE_JOBADVERTISEMENT_SQL_QUERY = "INSERT INTO jobadvertisements"
			+ "(fk_user_id,fk_location_id,fk_education_id,fk_workposition_id,fk_worktype_id,requiredexperience,title,resume) "
			+ "VALUES(" + "(SELECT user_id FROM users WHERE username=?),"
			+ "(SELECT location_id FROM locations WHERE city=? AND country=?),"
			+ "(SELECT education_id FROM educations WHERE education=?),"
			+ "(SELECT workposition_id FROM workpositions WHERE workposition=?),"
			+ "(SELECT worktype_id FROM worktypes WHERE worktype=?)," + "?,?,?" + ");";

	public static final String JOBADVERTISEMENTS_CARRERFIELDS_CONNECTION_SQL_QUERY = "INSERT INTO jobadvertisements_carrerfields(fk_jobadvertisement_id,fk_carrerfield_id) "
			+ "VALUES(?,?);";

	public static final String RETRIEVE_ALL_JOBADVERTISEMENTS_SQL_QUERY = "SELECT jobadvertisement_id,username,usertype,city,country,education,workposition,worktype,requiredexperience,title,resume "
			+ "FROM jobadvertisements "
			+ "INNER JOIN users ON users.user_id=jobcvs.fk_users_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=fk_education_id "
			+ "INNER JOIN workpositions ON workpositions.workposition_id=fk_workposition_id "
			+ "INNER JOIN worktypes ON worktypes.worktype_id=fk_worktype_id;";

	public static final String RETRIEVE_ALL_CARRERFIELDS_BY_JOBADVERTISEMENTS_SQL_QUERY = "SELECT carrerfield FROM carrerfields "
			+ "INNER JOIN jobadvertisements_carrerfields ON carrerfields.carrerfield_id=jobadvertisements_carrerfields.fk_carrerfield_id "
			+ "INNER JOIN jobadvertisements ON ?=jobadvertisements_carrerfields.fk_jobcv_id;";

	public static final String UPDATE_JOBADVERTISEMENT_SQL_QUERY = "UPDATE jobadvertisements" + "inner join locations "
			+ "inner join educations " + "inner join workpositions " + "inner join worktypes " + "set "
			+ "jobadvertisements.fk_location_id=locations.location_id,"
			+ "jobadvertisements.fk_education_id=educations.education_id,"
			+ "jobadvertisements.fk_workposition_id=workpositions.workposition_id,"
			+ "jobadvertisements.fk_worktype_id=worktypes.worktype_id," + "jobadvertisements.requiredexperience=?,"
			+ "jobadvertisements.title=?," + "jobadvertisements.resume=? "
			+ "where locations.city=? and locations.country=? and educations.education=?"
			+ "and workpositions.workposition=? and worktypes.worktype=?;";

	public static final String RETRIEVE_JOBADVERTISEMENT_BY_ID_SQL_QUERY = "SELECT jobadvertisement_id,username,usertype,city,country,education,workposition,worktype,requiredexperience,title,resume"
			+ "FROM jobadvertisements "
			+ "INNER JOIN users ON users.user_id=jobadvertisements.fk_users_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=fk_education_id "
			+ "INNER JOIN workpositions ON workpositions.workposition_id=fk_workposition_id "
			+ "INNER JOIN worktypes ON worktypes.worktype_id=fk_worktype_id"
			+ "WHERE jobadvertisements.jobadvertisement_id=?;";
}
