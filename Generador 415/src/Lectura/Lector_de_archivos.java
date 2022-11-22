package Lectura;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.apache.commons.math3.distribution.*;

import Lectura.Renumerador;
public class Lector_de_archivos {
//Renumerador ren = new Renumerador();
	String MX2;
	String MX3;
	public static void main(String[] args) throws FileNotFoundException {
	
		Calendar fecha = Calendar.getInstance();
		fecha.add(Calendar.DAY_OF_MONTH, -1);
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        
        String fechaHeader=dia+"-"+mes+"-"+año+"_";
        String fechaFormato=año+""+mes+""+dia;
        System.out.println(fechaHeader);
        LeerHeaderOpti(fechaHeader, fechaFormato);
		/*for(int i = 0;i<5;i++) {
			if(i==0) {
			modificarHeaderSwap("");
			}else {
				modificarHeaderSwap(i+"");
			}
		}*/
		//LeerConciSwap();
		//LeerConciOpti();
		/*for(int i = 0;i<5;i++) {
			if(i==0) {
			LeerHeaderSwap("");
			}else {
				LeerHeaderSwap(i+"");
			}
		}*/
		/*for(int i = 0;i<5;i++) {
			if(i==0) {
			LeerHeaderOpti("");
			}else {
				LeerHeaderOpti(i+"");
			}
		}*/
		//LeerFlows();
		//modificarHeaderSwap();
		//modificarFlows();
		//validarHeaderSwap();
		
	}
public String[] LeerMX2(String NoBoletaMX2,String ruta)throws FileNotFoundException{
	BufferedReader bufferedReader = null;
    String[] data = null;
    List<String> cleanData = new ArrayList<String>();
    String[] NbMx2;
    try {
        bufferedReader = new BufferedReader(new FileReader(ruta));
        String linea = bufferedReader.readLine();
        
        String numObt;
        String NoBolCSV;
        while(linea != null) {
        	String linea1 = linea.trim();
        	 String [] fields = linea1.split(";");
        	 for(int i=0;i<fields.length;i++) {
        		 fields[i]=fields[i].trim();
        	 }
        	 numObt = fields[0];
        	 NoBolCSV = NoBoletaMX2;
        	 if(numObt.equals(NoBolCSV)) {
        		 return NbMx2=fields;       		
        	 }else  {
             linea = bufferedReader.readLine();
        	 }
        }
       bufferedReader.close(); 
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        return null;
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }

    if (!cleanData.isEmpty()) {
        for (String row : cleanData) {
            System.out.println(row);
            
        }
        return null;
    }

    if (bufferedReader != null) {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 return null;
}

public static void LeerHeaderOpti(String fechaHeader, String fechaFormato )throws FileNotFoundException{
	String[] cabecerosHeader = {"Trn#_(Internal)","Trn#_(External)","Trn.Fmly","Trn.Group","Trn.Type","Counterpart","Counterpart_label","Portfolio","BO_DATE","BUY/SELL","Call/Put_(C/P)","American/European(A/E)","Delivery/Settlement(D/S)","Initial_payment","Initial_payment_cur","Initial_paydate","Trn.Date","Instrument","Nominal_cur","Spot","Forward","Days_to_Maturity","Period_exp_date","Conv_FXBase_1","Conv_FxPric_2","Market_vol","VolM","Strike","PL_cur","Fin_cash_bal","Past_Flow_NotFin","Brok_fees","Fin_brok_fees","Nonfin_cash_bal","PC_Cap_(NonFin)","PC_Rev_(NonFin)","Fut_Proc_Cap(NonDis)","Fut_Proc_Rev(NonDis)","PVE","Non_disc_MV","Discounted_MV","P&L","Delta","Gamma","Rho","Theta","Vega","Factor_Desc_Div_1","Factor_Desc_Div_2","P&L_contrav_COP","Nom_Div_Suby","Cur_Subyacente","Nom_Div_Base","Cur_Base","TC_Forward_TRM","Ini_pay_cont_COP","Tasa_desc_div_base","Tasa_desc_div_sub","Div_P&L_cont_COP"};
	
	Lector_de_archivos leerMx = new Lector_de_archivos();
	//BufferedReader bufferedReaderMx3 = null;
	//BufferedReader bufferedReaderMx2 = null;
	BufferedReader bufferedReader = null;
	BufferedWriter bufferedWriter = null;
    String[] data = null;
    List<String> cleanData = new ArrayList<String>();
    String NoBoletaMx3;
    String comparacionMx2="";
    String comparacionMx3="";
    String cabeHeader="";
    try {
        bufferedReader = new BufferedReader(new FileReader(".\\Archivos\\Murex3\\Formato415_Desarrollo.csv"));
        bufferedWriter = new BufferedWriter(new FileWriter(".\\Archivos\\Murex3\\FORMATO415_"+fechaFormato+"-modifiado.csv"));
        String lineaRenum = bufferedReader.readLine();
        final double PI = 3.1416;
        final int BaseDias = 360;
        
        while(lineaRenum != null) {
        	 String [] fields = lineaRenum.split(";");
        	 NoBoletaMx3 = fields[0];  	 
           	 String[] infoMx3=leerMx.LeerMX2(NoBoletaMx3,".\\Archivos\\Murex3\\"+fechaHeader+"dialogo_header_opti.txt");
        	 String NumMx3 = Arrays.toString(infoMx3);
        	 System.out.println(NoBoletaMx3);
        	 //Datos para calcular griegas
        	 if(infoMx3 != null) {
        	 System.out.println(NoBoletaMx3);
        	 double Spot = Double.parseDouble(infoMx3[19]);
        	 double Days_to_Maturity = Double.parseDouble(infoMx3[21]);
        	 double MarketVolume = Double.parseDouble(infoMx3[25]);
        	 double Strike = Double.parseDouble(infoMx3[27]);
        	 double Tasa_desc_div_base = Double.parseDouble(infoMx3[56]);
        	 double Tasa_desc_div_sub = Double.parseDouble(infoMx3[57]);
        	 String BUY_SELL = infoMx3[9];
        	 String CALL_PUT = infoMx3[10];
        	 double Days = Days_to_Maturity/360;
        	 System.out.println(Spot+" "+Days_to_Maturity+" "+MarketVolume+" "+Strike+" "+Tasa_desc_div_base+" "+Tasa_desc_div_sub+" "+BUY_SELL+" "+CALL_PUT+" "+Days);
        	 //DatosD1=[spot,days to Maturity,Market Volume,Strike,Tasa_desc_div_base,Tasa_desc_div_sub}
        	 double D1;     
        	 //DatosD2 = [days to Maturity, MarketVolume]
        	 double D2;
        	 //ND1=[PI,D1]
        	 double ND1;
        	 //ND2=[PI,D2]
        	 double ND2;
        	 //Delta=[BUY_SELL,CALL_PUT,D1]
        	 double Delta;
        	 //Gamma=[Spot,Days_toMaturity.Market Volume,ND1]
           	 double Gamma;
           	 //Vega = [Spot,Days_to_Maturity,ND1]
           	 double Vega;
           	 //Theta=[CALL_PUT,Spot,Days_to_Maturity,MarketVolume,Strike,Tasa_desc_div_base,Tasa_desc_div_sub,D2,ND1]
           	 double Theta;
           	 //Rho = [CALL-PUT,Days_To_Maturity,Strike,Tasa_desc_div_base,Tasa_desc_div_sub,D2]
           	 double Rho;
           	 
           	 comparacionMx3 = fields[0]+";"+fields[1]+";"+fields[2]+";"+fields[3]+";"+fields[4]+";"+fields[5]+";"+fields[6]+";"+fields[7]+";"+fields[8]+";"+fields[9];
        	 bufferedWriter.write(comparacionMx3+"\n");
        	 System.out.println(comparacionMx3);
        	 }else {
        	 System.out.println("dato no encontrado");
        	 comparacionMx3 = fields[0]+";"+fields[1]+";"+fields[2]+";"+fields[3]+";"+fields[4]+";"+fields[5]+";"+fields[6]+";"+fields[7]+";"+fields[8]+";"+fields[9];
        	 bufferedWriter.write(comparacionMx3+"\n");
        	 System.out.println(comparacionMx3);
        	 }
        	 
        	 
             //System.out.println(Arrays.toString(fields));
        	 comparacionMx2="";
        	 comparacionMx3="";
             lineaRenum = bufferedReader.readLine();}
             
        
        bufferedWriter.close();
        bufferedReader.close();
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



public static void modificarHeaderSwap(String conteo)throws FileNotFoundException{
	String[] cabecerosHeader = {"Trn#_(Internal)","Trn#_(External)","Trn.Fmly","Trn.Group","Trn.Type","Counterpart","Counterpart_label","Portfolio","BO_DATE","BUY/SELL","Trn.Date","Instrument","Nominal_currency","Main_index(1st_leg)","Main_index(2nd_leg)","Current_Cap(1st_leg)","Current_Cap(2nd_leg)","Cap_Vivo_Leg1","Cap_Vivo_leg_2","Pay/Rec(1_FixR)","Pay/Rec(2_FixR)","ExchangeCOP(Leg1)","ExchangeCOP(Leg2)","Maturity1","Maturity2","Days_to_Maturity","Period_exp_date","Leg_1_cur","Initial_Cap(1st_leg)","Leg_2_cur","Initial_Cap(2nd_leg)","Conv_FXBase_1","Conv_FxPric_2","PL_cur","Leg1_nondisc_NPV","Leg2_nondisc_NPV","Fin_cash_bal","Past_Flow_NotFin","Brok_fees","Financing_brok_fees","Nonfin_cash_bal","PastCash_Cap_(NonFin.)","PastCash_Rev_(NonFin.)","Fut_Proc_Cap(NonDis.)","Fut_Proc_Rev(NonDis.)","PVE","Non_disc_MV","Discounted_MV","P&L","Theta","ZC_Sensitivity","ZC_Convexity","P&L_Theta","NPV_Leg1","NPV_Leg_2","NPV_Leg_1_COP","NPV_Leg_2_COP","Theta_Leg_1","Theta_Leg_2","MV_COP","Collateral","Num_credito_red","Indexed 0","Indexed 1"};
		
	Lector_de_archivos leerMx = new Lector_de_archivos();
	//BufferedReader bufferedReaderMx3 = null;
	//BufferedReader bufferedReaderMx2 = null;
	BufferedReader bufferedReader = null;
	BufferedWriter bufferedWriter = null;
    String[] data = null;
    List<String> cleanData = new ArrayList<String>();
    String NoBoletaMx3;
    String DatoContraparte;
    String comparacionMx2="";
    String comparacionMx3="";
    String cabeHeader="";
    try {
    	//bufferedReaderMx3 = new BufferedReader(new FileReader(".\\Archivos\\Murex3\\dialogo_header_opti.txt"));
        //bufferedReaderMx2 = new BufferedReader(new FileReader(".\\Archivos\\Murex2\\dialogo_header_opti.txt"));
        bufferedReader = new BufferedReader(new FileReader(".\\Archivos\\Murex3\\dialogo_header_swap_bas.txt"));
        bufferedWriter = new BufferedWriter(new FileWriter(".\\Archivos\\Murex3\\ResultadoHeaderSwapRenum"+conteo+".txt"));
        //String lineaHeader = bufferedReaderMx3.readLine();
        String lineaRenum = bufferedReader.readLine();
        float sumaPyLMx2 =0;
   	 float sumaPyLMx3 =0;
        while(lineaRenum != null) {
        	 
        	 String [] fields = lineaRenum.split(";");
        	 NoBoletaMx3 = fields[0];
        	 DatoContraparte = fields[6];
        	// String[] infoMx2=leerMx.LeerMX2(NoBoletaMx2,".\\Archivos\\Murex2\\dialogo_header_swap.txt");
        	 String[] infoMx3=leerMx.LeerMX2(NoBoletaMx3,".\\Archivos\\Murex3\\dialogo_header_swap"+conteo+".txt");
        	 //String NumMx2 = Arrays.toString(infoMx2);
        	 String NumMx3 = Arrays.toString(infoMx3);
        	 //double desviacion = 0;
        	 //System.out.println(NumMx2 +"\n"+ NumMx3);
        	
        	 if(infoMx3!=null) {
        		 
        	 for(int i = 0;i<64;i++) {
        		 
     		  	if(i==6) {
     		  	comparacionMx3+=DatoContraparte+"; ";
     		  	
     		  	}else {
        	    comparacionMx3 += infoMx3[i]+"; ";
        	        }  //}
        	 }
        	 bufferedWriter.write(comparacionMx3+"\n");
        	
        	 
        	 }
        	 System.out.println(comparacionMx3+"\n");
        	 
             //System.out.println(Arrays.toString(fields));
        	 comparacionMx2="";
        	 comparacionMx3="";
             lineaRenum = bufferedReader.readLine();
             
        }
        bufferedWriter.close();
        bufferedReader.close();
       
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

