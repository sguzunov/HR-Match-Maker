package persistence;

public abstract class JobAccountDao extends Dao {

	public JobAccountDao(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	public abstract <E> E getById(int id);
}
