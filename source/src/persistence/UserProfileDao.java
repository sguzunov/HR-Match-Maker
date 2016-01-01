package persistence;

import persistence.sources.DataSource;

public abstract class UserProfileDao extends Dao {
	protected final static String FIRSTNAME_COLUMN = "firstname";
	protected final static String LASTNAME_COLUMN = "lastname";
	protected final static String WEBSITE_COLUMN = "website";

	public UserProfileDao(DataSource dataSource) {
		super(dataSource);
	}

	public abstract <E> E getByName(String name);
}
