package vue;
import javax.swing.table.*;
import java.util.*;

public class UneditableTableModel extends AbstractTableModel{
	private ArrayList<String[]> datas;
	private String[] columnNames;
	
	public UneditableTableModel(ArrayList<String[]> datas,String[] columnNames){
		super();
		this.columnNames=columnNames;
		this.datas = datas;
	}
	
	public int getRowCount() {
		return this.datas.size();
	}
	 
	public int getColumnCount() {
		return this.columnNames.length;
	}
		
	public void setDatas(ArrayList<String[]> tableau) {
		datas = tableau;
	}
	 
	public Object getValueAt(int r, int c) {
		return datas.get(r)[c];
	}
	 
	public String getColumnName(int c) {
		return columnNames[c];
	}
	/* 
	public boolean isCellEditable(int row, int col) {
	            return false;
	}
	*/
}
