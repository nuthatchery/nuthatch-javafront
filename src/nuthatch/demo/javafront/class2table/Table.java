package nuthatch.demo.javafront.class2table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {
	private List<Column> columns;
	private String name;


	public Table(Column... columns) {
		this.columns = new ArrayList<>(Arrays.asList(columns));
	}


	public Table(String tableName) {
		this.name = tableName;
		this.columns = new ArrayList<Column>();
	}


	public void addColumn(Column c) {
		columns.add(c);
	}


	public String toSQL() {
		StringBuffer result = new StringBuffer();
		result.append("CREATE TABLE ");
		result.append(name);
		result.append("(id INT PRIMARY KEY AUTOINCREMENT");
		for(Column c : columns) {
			result.append(", ");
			result.append(c.toSQL());
		}
		result.append(");");
		return result.toString();
	}
}
