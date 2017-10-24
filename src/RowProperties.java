
public class RowProperties {
	public static final char DEFAULT_COLUMN_DIVIDER = '|';
	public static final char DEFAULT_BORDER_CHAR = '-';
	private int totalLength;
	private int totalColums;
	private char columnDivider = DEFAULT_COLUMN_DIVIDER;
	private char rowBorderChar = DEFAULT_BORDER_CHAR;

	public char getRowBorderChar() {
		return rowBorderChar;
	}

	public void setRowBorderChar(char rowBorderChar) {
		this.rowBorderChar = rowBorderChar;
	}

	public RowProperties(int totalLength, int totalColums, char columnDivider)
			throws Exception {
		this.totalLength = totalLength;
		this.totalColums = totalColums;
		this.columnDivider = columnDivider;
		if (totalLength <= 0)
			throw new Exception(
					"Total Row length should be greater than zero!!!");
		if (totalColums <= 0)
			throw new Exception(
					"Total number of columns should be greater than zero!!!");

	}

	public int getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}

	public int getTotalColums() {
		return totalColums;
	}

	public void setTotalColums(int totalColums) {
		this.totalColums = totalColums;
	}

	public char getColumnDivider() {
		return columnDivider;
	}

	public void setColumnDivider(char columnDivider) {
		this.columnDivider = columnDivider;
	}

}
