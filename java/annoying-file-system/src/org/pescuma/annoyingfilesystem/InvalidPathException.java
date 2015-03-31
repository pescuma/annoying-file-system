package org.pescuma.annoyingfilesystem;

public class InvalidPathException extends RuntimeException {
	
	private static final long serialVersionUID = 471887281319072932L;
	
	public InvalidPathException(String path, char[] invalidChars, String[] aditionalValidations) {
		super(createMessage(path, invalidChars, aditionalValidations));
	}
	
	private static String createMessage(String path, char[] invalidChars,
			String[] aditionalValidations) {
		StringBuilder msg = new StringBuilder();
		msg.append("Invalid path: ").append(path);
		
		if (invalidChars != null && invalidChars.length > 0) {
			msg.append("\n").append("It can not contain any of: ");
			for (char c : invalidChars)
				msg.append(' ').append(c);
		}
		
		if (aditionalValidations != null)
			for (String val : aditionalValidations)
				msg.append("\n").append(val);
		
		return msg.toString();
	}
}
