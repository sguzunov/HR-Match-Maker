package persistence;

import persistence.sources.DataSource;

public abstract class JobAccountDao extends Dao {
	protected static final String WORKPOSITION_COLUMN = "workposition";
	protected static final String WORKTYPE_COLUMN = "worktype";
	protected static final String REQUIREDEXPERIENCE_COLUMN = "requiredexperience";

	public JobAccountDao(DataSource dataSource) {
		super(dataSource);
	}

	public abstract <E> E getById(int id);
}
