package report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ReportGenerator {
	static List<String> studentName = new ArrayList<>();
	static List<Integer> termMarks = new ArrayList<>();
	static List<String> result = new ArrayList<>();
	
	static void readFile(BufferedReader br) {
		String inputStr = null;
		try {
			while(br.ready()) {
				inputStr = br.readLine();
				System.out.println(inputStr);
				
				if (checkInputFormat(inputStr)) {
					Pattern splitpattern = Pattern.compile(":");
					String[] inputArray = splitpattern.split(inputStr);
					calculateTotalMarks(inputArray);
				} else {
					System.out.println("Invalid Input");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static void writeFile() {
		String outputFileName = "C:\\Users\\Jiwon Han\\Desktop\\Report.txt";
		try(FileWriter fw = new FileWriter(outputFileName); 
				BufferedWriter bw = new BufferedWriter(fw);) {
			for (int j = 0; j < result.size(); j++) {
				bw.write(result.get(j));
				bw.newLine();
			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	
	static boolean checkInputFormat(String inputString) {
		String patternString = "(^[a-zA-Z]+)([\b \b])([a-zA-Z]+)(:Term[0-9])(:[A-Za-z]+:[0-9]+){1,}$";
		Pattern inputPattern = Pattern.compile(patternString);
		Matcher match = inputPattern.matcher(inputString);
		return match.matches();
	}
	
	static void calculateTotalMarks(String[] marks) {
		int totalMarks = 0;
		for (int i = 2; i < marks.length; i++) {
			if (i % 2 != 0 ) {
				totalMarks += Integer.parseInt(marks[i]);
			}
		}
		
		if (!studentName.contains(marks[0])) {
			studentName.add(marks[0]);
			termMarks.add(totalMarks);
		} else {
			int index = studentName.indexOf(marks[0]);
			totalMarks += termMarks.get(index);
			termMarks.remove(index);
			termMarks.add(index, totalMarks);
		}
	}
	public static void main(String[] args) throws IOException {
		String inputFileName = "C:\\Users\\Jiwon Han\\Desktop\\Input.txt";
		try (Reader fr = new FileReader(inputFileName); BufferedReader br = new BufferedReader(fr);) {
			readFile(br);
			Student[] s = new Student[studentName.size()];
			for (int i = 0; i < s.length; i++ ) {
				s[i] = new Student();
				s[i].setStudentName(studentName.get(i));
				s[i].setMarks(termMarks.get(i));
				s[i].calculatePercentage();
				result.add(s[i].toString());
			}
			writeFile();
		} catch (IOException | PatternSyntaxException e) {
			e.printStackTrace();
		}
	}
}
