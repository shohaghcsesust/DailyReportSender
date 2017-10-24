import java.util.ArrayList;
import java.util.List;

public class StringFormat {
	private static String printDotLine(char dotChar, int totalLineLength) {
		String padText = "";
		for (int i = 0; i < totalLineLength; i++) {
			padText += dotChar;
		}
		return padText;

	}

	private static int nextWhitespacePos(String text, int maxStrLength) {
		if (text == null)
			text = "";
		boolean isWhitespaceFound = false;
		int lineLengthPos = maxStrLength;
		if (text.indexOf(' ') > 0) {
			while (!isWhitespaceFound) {
				if (Character.isWhitespace(text.charAt(lineLengthPos))) {
					isWhitespaceFound = true;
					break;
				}
				lineLengthPos++;
			}
		} else if (text.length() < maxStrLength) {
			lineLengthPos = text.length();
		}
		return lineLengthPos;
	}

	private static int prevWhitespacePos(String text, int maxStrLength) {
		if (text == null)
			text = "";
		boolean isWhitespaceFound = false;
		int lineLengthPos = maxStrLength;
		if (text.indexOf(' ') > 0) {
			while (!isWhitespaceFound) {
				if (Character.isWhitespace(text.charAt(lineLengthPos))) {
					isWhitespaceFound = true;
					break;
				}
				lineLengthPos--;
			}
		} else if (text.length() < maxStrLength) {
			lineLengthPos = text.length();
		}
		return lineLengthPos;
	}

	private static String leftPad(String text, char lPadChar, int maxTextlength) {
		if (text == null)
			text = "";
		int padCharlength = maxTextlength - text.length();
		return paddingText(padCharlength, lPadChar) + text;

	}

	private static String rightPad(String text, char rPadChar, int maxTextlength) {
		if (text == null)
			text = "";
		int padCharlength = maxTextlength - text.length();
		return text + paddingText(padCharlength, rPadChar);
	}

	private static String centerPad(String text, char padChar, int maxTextlength) {
		if (text == null)
			text = "";
		int padCharlength = maxTextlength - text.length();
		int padVal = Math.round(padCharlength / 2);
		String genText = "";
		if (padVal <= padCharlength) {
			genText = paddingText(padVal, padChar) + text
					+ paddingText(padCharlength - padVal, padChar);
		} else
			genText = text;
		return genText;
	}

	public static List<String> splitText(String text, int maxLength) {
		if (text == null)
			text = "";
		int textLength = text.length();
		int totalProcessed = 0;
		List<String> splitedTextList = new ArrayList<String>();
		if (text.trim().length() > maxLength) {
			while (totalProcessed <= textLength) {
				if (text.length() > maxLength) {
					int splitPos = prevWhitespacePos(text, maxLength);
					splitedTextList.add(text.substring(0, splitPos));
					text = text.substring(splitPos + 1);
					totalProcessed += splitPos;
				} else {
					splitedTextList.add(text);
					break;
				}
			}

		} else {
			splitedTextList.add(text);
		}

		return splitedTextList;
	}

	private static String paddingText(int charlength, char padChar) {
		String padText = "";
		for (int i = 0; i < charlength; i++) {
			padText += padChar;
		}
		return padText;
	}

	public static String paddedText(String text, ColumnProperties columnProp) {
		if (columnProp.getPaddingPos() == ColumnProperties.PADING_POS_LEFT)
			return leftPad(text, columnProp.getPadChar(),
					columnProp.getLength());
		else if (columnProp.getPaddingPos() == ColumnProperties.PADING_POS_RIGHT)
			return rightPad(text, columnProp.getPadChar(),
					columnProp.getLength());
		else if (columnProp.getPaddingPos() == ColumnProperties.PADING_POS_CENTER)
			return centerPad(text, columnProp.getPadChar(),
					columnProp.getLength());
		else
			return rightPad(text, columnProp.getPadChar(),
					columnProp.getLength());
	}

}
