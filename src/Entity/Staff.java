package Entity;

public class Staff {
	private int mId;
	private String mName;
	
	public Staff(int id,String name){
		mId = id;
		mName = name;
	}

	public int getId() {
		return mId;
	}

	public void setmId(int id) {
		mId = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}
	
	
	
}
