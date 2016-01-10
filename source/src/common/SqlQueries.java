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

	public static final String RETRIEVE_ALL_USERS_SQL_QUERY = "SELECT username,password,usertype " + "FROM users "
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

	public static final String RETRIEVE_ALL_JOBCVS_SQL_QUERY = "SELECT jobcv_id,user_id,username,usertype,city,country,education,workposition,worktype,requiredexperience"
			+ "FROM jobcvs "
			+ "INNER JOIN users ON users.user_id=jobcvs.fk_users_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=fk_education_id "
			+ "INNER JOIN workpositions ON workpositions.workposition_id=fk_workposition_id "
			+ "INNER JOIN worktypes ON worktypes.worktype_id=fk_worktype_id;";

	public static final String RETRIEVE_ALL_CARRERFIELDS_BY_JOBCVS_ID_SQL_QUERY = "SELECT carrerfield FROM carrerfields "
			+ "INNER JOIN jobcvs_carrerfields ON carrerfields.carrerfield_id=jobcvs_carrerfields.fk_carrerfield_id "
			+ "INNER JOIN jobcvs ON ?=jobcvs_carrerfields.fk_jobcv_id;";

	public static final String RETRIEVE_ALL_LANGUAGES_BY_JOBCV_ID_SQL_QUERY = "SELECT language FROM languages "
			+ "INNER JOIN jobcvs_languages ON languages.language_id=jobcvs_languages.fk_language_id "
			+ "INNER JOIN jobcvs ON ?=jobcvs_languages.fk_jobcv_id;";

	public static final String UPDATE_JOBCV_SQL_QUERY = "UPDATE jobcvs" + "inner join locations "
			+ "inner join educations " + "inner join workpositions " + "inner join worktypes " + "set "
			+ "jobcvs.fk_location_id=locations.location_id," + "jobcvs.fk_education_id=educations.education_id,"
			+ "jobcvs.fk_workposition_id=workpositions.workposition_id,"
			+ "jobcvs.fk_worktype_id=worktypes.worktype_id," + "jobcvs.requiredexperience=?," + "jobcvs.age=?"
			+ "where locations.city=? and locations.country=? and educations.education=?"
			+ "and workpositions.workposition=? and worktypes.worktype=?;";

	public static final String RETRIEVE_JOBCV_BY_ID_SQL_QUERY = "SELECT jobcv_id,user_id,username,usertype,city,country,education,workposition,worktype,requiredexperience,age"
			+ "FROM jobcvs "
			+ "INNER JOIN users ON users.user_id=jobcvs.fk_user_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=fk_education_id "
			+ "INNER JOIN workpositions ON workpositions.workposition_id=fk_workposition_id "
			+ "INNER JOIN worktypes ON worktypes.worktype_id=fk_worktype_id" + "WHERE jobcvs.jobcv_id=?;";

	// JobAdvertisement queries.
	public static final String CREATE_JOBADVERTISEMENT_SQL_QUERY = "INSERT INTO jobadvertisements"
			+ "(fk_user_id,fk_location_id,fk_education_id,fk_workposition_id,fk_worktype_id,requiredexperience,title,resume) "
			+ "VALUES(" + "(SELECT user_id FROM users WHERE username=?),"
			+ "(SELECT location_id FROM locations WHERE city=? AND country=?),"
			+ "(SELECT education_id FROM educations WHERE education=?),"
			+ "(SELECT workposition_id FROM workpositions WHERE workposition=?),"
			+ "(SELECT worktype_id FROM worktypes WHERE worktype=?)," + "?,?,?" + ");";

	public static final String RETRIEVE_ALL_JOBADVERTISEMENTS_SQL_QUERY = "SELECT jobadvertisement_id,user_id,username,usertype,city,country,education,workposition,worktype,requiredexperience,title,resume "
			+ "FROM jobadvertisements "
			+ "INNER JOIN users ON users.user_id=jobcvs.fk_users_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=fk_education_id "
			+ "INNER JOIN workpositions ON workpositions.workposition_id=fk_workposition_id "
			+ "INNER JOIN worktypes ON worktypes.worktype_id=fk_worktype_id;";

	public static final String UPDATE_JOBADVERTISEMENT_SQL_QUERY = "UPDATE jobadvertisements" + "inner join locations "
			+ "inner join educations " + "inner join workpositions " + "inner join worktypes " + "set "
			+ "jobadvertisements.fk_location_id=locations.location_id,"
			+ "jobadvertisements.fk_education_id=educations.education_id,"
			+ "jobadvertisements.fk_workposition_id=workpositions.workposition_id,"
			+ "jobadvertisements.fk_worktype_id=worktypes.worktype_id," + "jobadvertisements.requiredexperience=?,"
			+ "jobadvertisements.title=?," + "jobadvertisements.resume=? "
			+ "where locations.city=? and locations.country=? and educations.education=?"
			+ "and workpositions.workposition=? and worktypes.worktype=?;";

	public static final String RETRIEVE_JOBADVERTISEMENT_BY_ID_SQL_QUERY = "SELECT jobadvertisement_id,user_id,username,usertype,city,country,education,workposition,worktype,requiredexperience,title,resume"
			+ "FROM jobadvertisements "
			+ "INNER JOIN users ON users.user_id=jobadvertisements.fk_user_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=fk_education_id "
			+ "INNER JOIN workpositions ON workpositions.workposition_id=fk_workposition_id "
			+ "INNER JOIN worktypes ON worktypes.worktype_id=fk_worktype_id"
			+ "WHERE jobadvertisements.jobadvertisement_id=?;";

	public static final String JOBADVERTISEMENTS_CARRERFIELDS_CONNECTION_SQL_QUERY = "INSERT INTO jobadvertisements_carrerfields(fk_jobadvertisement_id,fk_carrerfield_id) "
			+ "VALUES(?,?);";

	public static final String RETRIEVE_ALL_CARRERFIELDS_BY_JOBADVERTISEMENTS_ID_SQL_QUERY = "SELECT carrerfield FROM carrerfields "
			+ "INNER JOIN jobadvertisements_carrerfields ON carrerfields.carrerfield_id=jobadvertisements_carrerfields.fk_carrerfield_id "
			+ "INNER JOIN jobadvertisements ON ?=jobadvertisements_carrerfields.fk_jobcv_id;";

	// JobSeeker queries.
	public static final String CREATE_JOBSEEKERPROFILE_SQL_QUERY = "INSERT INTO jobseekerprofiles"
			+ "(fk_user_id,firstname,lastname,fk_location_id,website,age,fk_education_id) " + "VALUES("
			+ "(SELECT user_id FROM users WHERE username=?)," + "?,?,"
			+ "(SELECT location_id FROM locations WHERE city=? AND country=?)," + "?,?,"
			+ "(SELECT education_id FROM educations WHERE education=?));";

	public static final String RETRIEVE_ALL_JOBSEEKERPROFILES_SQL_QUERY = "SELECT profile_id,user_id,username,usertype,firstname,lastname,city,country,website,age,education "
			+ "FROM jobseekerprofiles "
			+ "INNER JOIN users ON users.user_id=jobseekerprofiles.fk_user_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_usertype_id "
			+ "INNER JOIN locations ON locations.location_id=jobseekerprofiles.fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=jobseekerprofiles.fk_education_id;";

	public static final String UPDATE_JOBSEEKERPROFILE_SQL_QUERY = "UPDATE jobseekerprofiles" + "inner join locations "
			+ "inner join educations " + "set " + "jobseekerprofiles.firstname=?," + "jobseekerprofiles.lastname=?,"
			+ "jobseekerprofiles.fk_location_id=locations.location_id," + "jobseekerprofiles.website=?,"
			+ "jobseekerprofiles.age=?," + "jobseekerprofiles.fk_education_id=educations.education_id,"
			+ "where locations.city=? and locations.country=? and educations.education=?;";

	public static final String RETRIEVE_JOBSEEKERPROFILE_BY_ID_SQL_QUERY = "SELECT profile_id,user_id,username,usertype,firstname,lastname,city,country,website,age,education "
			+ "FROM jobseekersprofiles " + "INNER JOIN users ON users.user_id=jobseekersprofiles.fk_user_id "
			+ "INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=jobseekersprofiles.fk_location_id "
			+ "INNER JOIN educations ON educations.education_id=jobseekersprofiles.fk_education_id "
			+ "WHERE profile_id=?;";

	// Employer queries.
	public static final String CREATE_EMPLOYERPROFILE_SQL_QUERY = "INSERT INTO employerprofiles"
			+ "(fk_user_id,firstname,lastname,fk_location_id,website,companyname) " + "VALUES("
			+ "(SELECT user_id FROM users WHERE username=?)," + "?,?,"
			+ "(SELECT location_id FROM locations WHERE city=? AND country=?)," + "?,?;";

	public static final String RETRIEVE_ALL_EMPLOYERPROFILES_SQL_QUERY = "SELECT profile_id,user_id,username,usertype,firstname,lastname,city,country,website,companyname "
			+ "FROM employerprofiles "
			+ "INNER JOIN users ON users.user_id=employerprofiles.fk_user_id INNER JOIN usertypes ON usertypes.usertype_id=users.fk_usertype_id "
			+ "INNER JOIN locations ON locations.location_id=employerprofiles.fk_location_id;";

	public static final String EMPLOYERPROFILES_CARRERFIELDS_CONNECTION_SQL_QUERY = "INSERT INTO employerprofiles_carrerfields(fk_employerprofile_id,fk_carrerfield_id) "
			+ "VALUES(?,?);";

	public static final String RETRIEVE_ALL_CARRERFIELDS_BY_EMPLOYERPROFILE_ID_SQL_QUERY = "SELECT carrerfield FROM carrerfields "
			+ "INNER JOIN employerprofiles_carrerfields ON carrerfields.carrerfield_id=employerprofiles_carrerfields.fk_carrerfield_id "
			+ "INNER JOIN employerprofiles ON ?=employerprofiles_carrerfields.fk_profile_id;";

	public static final String UPDATE_EMPLOYERPROFILE_SQL_QUERY = "UPDATE employerprofiles" + "inner join locations "
			+ "set " + "employerprofiles.firstname=?," + "employerprofiles.lastname=?,"
			+ "employerprofiles.fk_location_id=locations.location_id," + "employerprofiles.website=?,"
			+ "employerprofiles.companyname=? " + "where locations.city=? and locations.country=?;";

	public static final String RETRIEVE_EMPLOYERPROFILE_BY_ID_SQL_QUERY = "SELECT profile_id,user_id,username,usertype,firstname,lastname,city,country,website,companyname "
			+ "FROM employerprofiles " + "INNER JOIN users ON users.user_id=employerprofiles.fk_user_id "
			+ "INNER JOIN usertypes ON usertypes.usertype_id=users.fk_userype_id "
			+ "INNER JOIN locations ON locations.location_id=employerprofiles.fk_location_id " + "WHERE profile_id=?;";
}
