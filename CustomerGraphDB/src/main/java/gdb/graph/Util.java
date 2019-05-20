package gdb.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import gdb.GDBException;

public class Util {

	public static Properties getPropperties(String configFileLocation) throws FileNotFoundException, IOException {
		Properties props = new Properties();	
	    File configFile = new File(configFileLocation);
	    InputStream stream = new FileInputStream(configFile);
	    props.load(stream);
		return props;
	}

	public static BufferedReader readFile(String fileLocation) throws GDBException {
		try {
			BufferedReader br =  new BufferedReader(new FileReader(new File(fileLocation)));
			//br.readLine();
			return br;
		} catch (Exception e) {
			throw new GDBException("Given file location is not correct or error in reading file : File Name :"+fileLocation, e) ;
		}
	}

}
