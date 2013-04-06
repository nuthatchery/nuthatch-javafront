package nuthatch.demo.javafront.class2table;

public class Column {
	private String name;
	private String type;
	private String foreignKey;


	public Column(String name, String type) {
		this.name = name;
		this.type = type;
	}


	public Column(String name, String type, String foreignKey) {
		this.name = name;
		this.type = type;
		this.foreignKey = foreignKey;
	}


	public String toSQL() {
		if(foreignKey != null) {
			return name + " " + type + " REFERENCES " + foreignKey + "(id)";
		}
		else {
			return name + " " + type;
		}
	}
}
