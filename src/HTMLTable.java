
public class HTMLTable {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append("<pre>");
		sb.append("<table align=\"center\">");
		//add table head
		sb.append("<th>");
		sb.append("Name");
		sb.append("</th>");

		sb.append("<th>");
		sb.append("Revision");
		sb.append("</th>");
		
		sb.append("<th>");
		sb.append("Message");
		sb.append("</th>");
		
		
		sb.append("<tr>");
		
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</pre>");
		System.out.println(sb.toString());
	}
}
