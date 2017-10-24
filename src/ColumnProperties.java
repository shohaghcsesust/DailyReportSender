

public class ColumnProperties {
	public static final int PADING_POS_LEFT = 1;
	public static final int PADING_POS_RIGHT = 2;
	public static final int PADING_POS_CENTER = 0;
	public static final char DEFAULT_PADDING_CHAR = ' ';
	public static final char DEFAULT_COLUMN_LENGTH = 20;
	private int length = DEFAULT_COLUMN_LENGTH;// Cell length
	private char padChar = DEFAULT_PADDING_CHAR;
	private int paddingPos = PADING_POS_RIGHT;
	/*
	 * for unique key/col value, if one cell value is same to another cell value
	 * . then we better need to set
	 */
	private String columnValue;
	public ColumnProperties(int length, char padChar, int paddingPos) {
		if (length > 0)
			this.length = length;
		this.padChar = padChar;
		if (paddingPos >= 0 && paddingPos < 3)
			this.paddingPos = paddingPos;

	}

	// for duplicate value/message/ same key value
	public ColumnProperties(String columnValue, int length, char padChar,
			int paddingPos) {
		if (length > 0)
			this.length = length;
		this.padChar = padChar;
		if (paddingPos >= 0 && paddingPos < 3)
			this.paddingPos = paddingPos;
		this.columnValue = columnValue == null ? " " : columnValue;

	}
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public char getPadChar() {
		return padChar;
	}

	public void setPadChar(char padChar) {
		this.padChar = padChar;
	}

	public int getPaddingPos() {
		return paddingPos;
	}

	public void setPaddingPos(int paddingPos) {
		this.paddingPos = paddingPos;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
}
