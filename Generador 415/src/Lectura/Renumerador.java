package Lectura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Renumerador {
	Lector_de_archivos lecArc = new Lector_de_archivos();
	public String[] nbMx2;
	public String[] nbMx3;
	public String[] getNbMx3() {
		return nbMx3;
	}
	public void setNbMx3(String[] nbMx3) {
		this.nbMx3 = nbMx3;
	}
	public String[] getNbMx2() {
		return nbMx2;
	}
	public void setNbMx2(String[] nbMx2) {
		this.nbMx2 = nbMx2;
	}
	public void Leer()throws FileNotFoundException{
		//BufferedReader bufferedReaderMx3 = null;
		//BufferedReader bufferedReaderMx2 = null;
		BufferedReader bufferedReader = null;
	    String[] data = null;
	    List<String> cleanData = new ArrayList<String>();
	    String[] MX2;
	    String[] MX3;
	    try {
	    	//bufferedReaderMx3 = new BufferedReader(new FileReader(".\\Archivos\\Murex3\\dialogo_header_opti.txt"));
	        //bufferedReaderMx2 = new BufferedReader(new FileReader(".\\Archivos\\Murex2\\dialogo_header_opti.txt"));
	        bufferedReader = new BufferedReader(new FileReader(".\\Archivos\\Murex3\\RenumCSV.txt"));
	        //String lineaHeader = bufferedReaderMx3.readLine();
	        String lineaRenum = bufferedReader.readLine();
	        while(lineaRenum != null) {
	        	 String [] fields = lineaRenum.split(";");
	        	 String NoBoletaMx3 = fields[0];
	        	 String NoBoletaMx2 = fields[2];
	        	 //LeerMX2(NoBoletaMx2);
	        	 //LeerMX3(NoBoletaMx3);
	             //System.out.println(Arrays.toString(fields));
	        	 System.out.println(NoBoletaMx3);
	             lineaRenum = bufferedReader.readLine();
	             
	        }
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    if (!cleanData.isEmpty()) {
	        for (String row : cleanData) {
	            System.out.println(row);
	        }

	    }

	    if (bufferedReader != null) {
	        try {
	            bufferedReader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
