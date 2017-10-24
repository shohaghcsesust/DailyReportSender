
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MessageProperties {
	LinkedHashMap<String, ColumnProperties> msgColumns;
	RowProperties msgRow;
	
	public MessageProperties(
			LinkedHashMap<String, ColumnProperties> msgColumns,
			RowProperties msgRow) throws Exception {
		this.msgColumns = msgColumns;
		this.msgRow = msgRow;
		if (msgRow.getTotalColums() != msgColumns.size())
			throw new Exception(
					"Total columns in row properties and number of column in message column mismatch");
	}

	public String getHeaderRowString() {
		Iterator<String> columnNames = msgColumns.keySet().iterator();
		StringBuffer msg = new StringBuffer();
		while (columnNames.hasNext()) {
			ColumnProperties colProps = msgColumns.get(columnNames.next());
			msg.append(msgRow.getColumnDivider()).append(
					MailStringFormat.paddedText(columnNames.next(), colProps));
		}
		msg.append(msgRow.getColumnDivider());
		return msg.toString();
	}

	public  String getMessageRow() {
		List<ColumnProperties> colProps = new ArrayList<ColumnProperties>();
		StringBuffer bMsg = new StringBuffer();
		int maxColHeight = getMaxTextLineCount(this.msgColumns);
		List<List<String>> rowDataList = getRowDataList(this.msgColumns,
				maxColHeight, colProps);
		for (int j = 0; j < maxColHeight; j++) {
			for (int i = 0; i < rowDataList.size(); i++) {
				bMsg.append(this.msgRow.getColumnDivider()).append(
						MailStringFormat.paddedText(rowDataList.get(i).get(j),
								colProps.get(i)));
			}
			bMsg.append(this.msgRow.getColumnDivider());
			bMsg.append("\n");
		}
		return bMsg.toString();
	}

	private static List<List<String>> getRowDataList(
			Map<String, ColumnProperties> msgColValue, int maxColHeight,
			List<ColumnProperties> colProps) {
		Iterator<String> columnVals = msgColValue.keySet().iterator();
		List<List<String>> rowDataList = new ArrayList<List<String>>();
		while (columnVals.hasNext()) {
			String key = columnVals.next();
			ColumnProperties valProp = msgColValue.get(key);
			colProps.add(valProp);
			String colVal="";
			if (valProp.getColumnValue() != null
					&& !valProp.getColumnValue().equals("")){
				colVal=valProp.getColumnValue();			 
			} else {
				colVal=key;
			}
			String[] splitedByNewLine = colVal.split("\n");
			List<String> splitedTextLists = new ArrayList<String>();
			for (int i = 0; i < splitedByNewLine.length; i++) {
				List<String> splitedTextList=MailStringFormat.splitText(splitedByNewLine[i], valProp.getLength());
				splitedTextLists.addAll(splitedTextList);
			}
			splitedTextLists = addAditionalValues(splitedTextLists, maxColHeight);
			rowDataList.add(splitedTextLists);
		}
		return rowDataList;
	}

	private static int getMaxTextLineCount(
			Map<String, ColumnProperties> msgColValue) {
		Iterator<String> columnValues = msgColValue.keySet().iterator();
		int maxColHeight = 0;
		while (columnValues.hasNext()) {
			String key = columnValues.next();
			ColumnProperties valProp = msgColValue.get(key);
			String colVal="";
			if (valProp.getColumnValue() != null
					&& !valProp.getColumnValue().equals("")) {
				colVal = valProp.getColumnValue();
			} else {
				colVal = key;
			}
			String[] splitedByNewLine = colVal.split("\n");
			List<String> splitedTextLists = new ArrayList<String>();
			for (int i = 0; i < splitedByNewLine.length; i++) {
				List<String> splitedTextList=MailStringFormat.splitText(splitedByNewLine[i], valProp.getLength());
				splitedTextLists.addAll(splitedTextList);
			}
			maxColHeight = splitedTextLists.size() > maxColHeight ? splitedTextLists.size() : maxColHeight;
		}
		return maxColHeight;
	}

	private static List<String> addAditionalValues(
			List<String> splitedTextList, int maxColHeight) {
		int listSize = splitedTextList.size();
		for (int i = 0; i < maxColHeight - listSize; i++) {
			splitedTextList.add(" ");
		}
		return splitedTextList;
	}

	public static String getBorderRow(RowProperties msgRow) {
		StringBuffer bMsg = new StringBuffer();
		for (int i = 0; i < msgRow.getTotalLength() + msgRow.getTotalColums(); i++) {
			bMsg.append(msgRow.getRowBorderChar());
		}
		return bMsg.toString();
	}
}
