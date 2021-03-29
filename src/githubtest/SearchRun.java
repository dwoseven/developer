package githubtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SearchRun {

	public static void main(String[] args) {
		String dirNm = 
				"C:/Users/USER/Documents/"
				+ "nexacro/17/outputs/testNxcr17";
		
		try {
			fileSearch(dirNm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private static void fileSearch(String dirNm) 
		throws IOException {
		File dir = new File(dirNm);
		File[] fileList = dir.listFiles();
		String fileName = "";
		String targetStr = "frm_Test.xfdl";
		ArrayList<String> ret = null;
		
		for(int i=0; i<fileList.length; i++) {
			File file = fileList[i];
			if(file.isFile()) {
				fileName = file.getName();
				if(fileName.startsWith("\\.")) {
					;
				} else {
					ret = searchStr(file.getAbsolutePath(), targetStr);
					if(ret.size() > 0) {
						System.out.println(file.getAbsolutePath());
					}
				}
			} else if(file.isDirectory()) {
				fileSearch(file.getCanonicalPath().toString());
			}
		}
		
	}

	private static ArrayList<String> searchStr(String fileNm, 
			String targetStr) {
		ArrayList<String> arr = new ArrayList<String>();
		BufferedReader inFile = null;
		try {
				inFile = new BufferedReader(
						new FileReader(new File(fileNm)));
				String sLine = null;
			
				while((sLine = inFile.readLine()) != null) {
					if(sLine.contains(targetStr)) {
						arr.add(sLine);
					}
				}
				
				inFile.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return arr;		
	}
}
