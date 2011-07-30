package com.anheinno.magadapter.lib;

import java.io.FileWriter;
import java.text.SimpleDateFormat;

import com.anheinno.magadapter.lib.MAGConfig;

public class MAGLog {
	
	public static void log(String msg) {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tempTime = new SimpleDateFormat("HH:mm:ss");
		String CurrentDate = tempDate.format(new java.util.Date());
		String CurrentTime = tempTime.format(new java.util.Date());

		String filepath = MAGConfig.getLOG_DIR() + "/" + "mag.log."
				+ CurrentDate + ".txt";

		try {
			FileWriter fw = new FileWriter(filepath, true);
			if (fw != null) {
				fw.append("[" + CurrentTime + "] " + msg + "\n");
				fw.close();
			}
		} catch (final Exception e) {

		}
	}

	public static void log(MAGRequest req, String msg) {
		log("(" + req.getPIN() + "/" + req.getHandle() + ")" + msg);
	}

}
