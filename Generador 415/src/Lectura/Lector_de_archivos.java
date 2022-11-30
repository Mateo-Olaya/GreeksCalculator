package Lectura;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.apache.commons.math3.distribution.*;

import Lectura.Renumerador;
public class Lector_de_archivos {
//Renumerador ren = new Renumerador();
	String MX2;
	String MX3;
	private static NormalDistribution nd;
	public static void main(String[] args) throws FileNotFoundException, ParseException {
	       
		String fechaActual;
		String fechaHeader;
		String fechaFormato;
		Calendar fecha = Calendar.getInstance();
		if(fecha.get(Calendar.DAY_OF_WEEK)== Calendar.MONDAY) {
			fecha.add(Calendar.DAY_OF_MONTH, -3);
	        int año = fecha.get(Calendar.YEAR);
	        int mes = fecha.get(Calendar.MONTH) + 1;
	        int dia = fecha.get(Calendar.DAY_OF_MONTH);
	        fechaHeader=dia+"-"+mes+"-"+año+"_";
	         fechaFormato=año+""+mes+""+dia;
	         fechaActual=dia+"/"+mes+"/"+año;
		}else {
		fecha.add(Calendar.DAY_OF_MONTH, -1);
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        fechaHeader=dia+"-"+mes+"-"+año+"_";
        fechaFormato=año+""+mes+""+dia;
        fechaActual=dia+"/"+mes+"/"+año;
		}
        
        System.out.println(fechaHeader);
        LeerHeaderOpti(fechaHeader, fechaFormato,fechaActual);
	    
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
public double[] interpolacion(double diasCalcular, String fechaFormato)throws FileNotFoundException, ParseException{
	BufferedReader bufferedReader = null;
    String[] data = null;
    List<String> cleanData = new ArrayList<String>();
    int[] NbMx2;
   
    
    try {
        bufferedReader = new BufferedReader(new FileReader("\\\\co.igrupobbva\\svrfilesystem\\me_apps\\AssetControl\\Colombia\\TVFI_TRNS_SW_GANADERO_REGUL.DAT"));//\\\\co.igrupobbva\\svrfilesystem\\me_apps\\AssetControl\\Colombia\\TVFI_TRNS_SW_GANADERO_REGUL.DAT
        String linea = bufferedReader.readLine();//\\\\82.0.53.175\\c$\\Users\\c808223\\Desktop\\TVFI_TRNS_SW_GANADERO_REGUL.DAT
        double[] xIBR = new double[100];
        double[] yIBR = new double[100];
        double[] xUIBR = new double[100];
        double[] yUIBR=new double[100];
        int conteoIBR =0;
        int conteoUIBR =0;
                while(linea != null) {
                	
                String tipoDato = linea.substring(8,12);
                if(tipoDato.equals("IBR ")) {
                	String añoFI = linea.substring(77,81);
                	String mesFI = linea.substring(81,83);
                	String diaFI = linea.substring(83,85);
                	String añoFF = linea.substring(91,95);
                	String mesFF = linea.substring(95,97);
                	String diaFF = linea.substring(97,99);
                	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                     Date firstDate = sdf.parse(diaFI+"/"+mesFI+"/"+añoFI);
                     Date secondDate = sdf.parse(diaFF+"/"+mesFF+"/"+añoFF);
                     long diff =  secondDate.getTime() -firstDate.getTime() ;
                     TimeUnit time = TimeUnit.DAYS; 
                     long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
                     xIBR[conteoIBR]=(int)diffrence;
                     double taza = Double.parseDouble(linea.substring(21,38))/100000000000.0;
                     yIBR[conteoIBR]= taza;
                     conteoIBR = conteoIBR + 1;
                     System.out.println(xIBR);
                     
                     for(int k=0;k<xIBR.length-1;k++) {
                         for(int f=0;f<xIBR.length-1-k;f++) {
                             if (xIBR[f]<xIBR[f+1]) {
                                 double auxdias=0;
                                 auxdias=xIBR[f];
                                 xIBR[f]=xIBR[f+1];
                                 xIBR[f+1]=auxdias;
                                 double auxtaza;
                                 auxtaza=yIBR[f];
                                 yIBR[f]=yIBR[f+1];
                                 yIBR[f+1]=auxtaza;
                             }
                         }
                     }
                     //linea = bufferedReader.readLine();   
                     
                }//else
                if(tipoDato.equals("UIBR")) {
                	String añoFI = linea.substring(77,81);
                	String mesFI = linea.substring(81,83);
                	String diaFI = linea.substring(83,85);
                	String añoFF = linea.substring(91,95);
                	String mesFF = linea.substring(95,97);
                	String diaFF = linea.substring(97,99);
                	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                     Date firstDate = sdf.parse(diaFI+"/"+mesFI+"/"+añoFI);
                     Date secondDate = sdf.parse(diaFF+"/"+mesFF+"/"+añoFF);
                     long diff = secondDate.getTime() - firstDate.getTime();
                     TimeUnit time = TimeUnit.DAYS; 
                     long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);	
                     xUIBR[conteoUIBR]=(int)diffrence;
                     double taza = Double.parseDouble(linea.substring(21,38))/100000000000.0;
                     yUIBR[conteoUIBR]= taza;
                     conteoUIBR = conteoUIBR + 1;
                     
                     //linea = bufferedReader.readLine();   
                     for(int h=0;h<conteoUIBR;h++) {
                         for(int f=0;f<conteoUIBR;f++) {
                             if (xUIBR[f]<xUIBR[f+1]) {
                                 double auxdias=0;
                                 auxdias=xUIBR[f];
                                 xUIBR[f]=xUIBR[f+1];
                                 xUIBR[f+1]=auxdias;
                                 double auxtaza=0;
                                 auxtaza=yUIBR[f];
                                 yUIBR[f]=yUIBR[f+1];
                                 yUIBR[f+1]=auxtaza;
                             }
                         }
                     }
                }
                linea = bufferedReader.readLine();  
                //return xIBR;
                
        }
                bufferedReader.close(); 
       boolean encontradoRD = false,encontradoRCS = false;
       double YRd = 0, YRCS = 0;
       int i = 0, j=0;
       if(encontradoRD == false && encontradoRCS==false) {
       
       while(i < xIBR.length-1 && encontradoRD == false ) {
    	   if(xIBR[i+1] <= diasCalcular && diasCalcular <= xIBR[i] ) {
    		   YRd=yIBR[i]+((yIBR[i-1] - yIBR[i])/(xIBR[i-1] - xIBR[i])*(diasCalcular - xIBR[i]));
    		   encontradoRD = true;
    	   }
    	   i++;
       }
       while(j< xUIBR.length-1 && encontradoRCS == false ) {
    	   if(xUIBR[j+1] <= diasCalcular && diasCalcular <= xUIBR[j] ) {
    		   YRCS=yUIBR[j]+((yUIBR[j-1] - yUIBR[j])/(xUIBR[j-1] - xUIBR[j])*(diasCalcular - xUIBR[j]));
    		   encontradoRCS = true;
    	   }
    	   j++;
       }
       double resultado[]= new double[2];
       resultado[0] = YRd/100;
       resultado[1] = YRCS/100;
       return resultado;}
       //return xIBR;

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
public static void LeerHeaderOpti(String fechaHeader, String fechaFormato, String fechaActual)throws FileNotFoundException, ParseException{
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
    	
        bufferedReader = new BufferedReader(new FileReader("\\\\co.igrupobbva\\svrfilesystem\\RECEPCION_HOST_OLD\\DIALOGO_MUREX\\FORMATO415_"+fechaFormato+".csv"));//\\\\co.igrupobbva\\svrfilesystem\\RECEPCION_HOST_OLD\\DIALOGO_MUREX\\FORMATO415_"+fechaFormato+".csv"
        bufferedWriter = new BufferedWriter(new FileWriter("\\\\82.0.54.124\\c$\\Users\\c782970\\Desktop\\FORMATO415_"+fechaFormato+".csv"));//\\\\82.0.53.175\\c$\\Users\\c808223\\Desktop\\FORMATO415_"+fechaFormato+".csv"
        String lineaRenum = bufferedReader.readLine();
        final double PI = 3.1416;
        final int BaseDias = 360;
        
        while(lineaRenum != null) {
        	 String [] fields = lineaRenum.split(";");
        	 NoBoletaMx3 = fields[0];  	 
           	 String[] infoMx3=leerMx.LeerMX2(NoBoletaMx3,"\\\\co.igrupobbva\\svrfilesystem\\tx\\envio_host\\DIALOGO_MUREX\\"+fechaHeader+"dialogo_header_opti.txt");//\\\\co.igrupobbva\\svrfilesystem\\tx\\envio_host\\"+fechaHeader+"dialogo_header_opti.txt
        	 String NumMx3 = Arrays.toString(infoMx3);
        	 System.out.println(NoBoletaMx3);
        	 String fecha = fechaFormato;
        	 //Datos para calcular griegas
        	 if(infoMx3 != null && Double.parseDouble(infoMx3[21])>0) {
        		 System.out.println(NoBoletaMx3);
            	 double Spot =0.0;
            	 double Days_to_Maturity=0.0;
            	 double MarketVolume=0.0;
            	 double Strike=0.0;
            	 double Tasa_desc_div_base=0.0;
            	 double Tasa_desc_div_sub=0.0;
            	 String BUY_SELL;
            	 String CALL_PUT;
            	 double Te=0.0;
            	 double DaysT2=0.0;
            	 double VolM=0.0;
            	 double diasCumplimiento=0.0;
            	 double Td=0.0;
            	 double Rd=0.0;
            	 double Rcs=0.0;
            	 double Rdif=0.0;
            	 double FwdPrice=0.0;
            	 double LnFwdStr=0.0;
            	 double VolTe=0.0;
            	 double SumLnVol=0.0;
            	 double VolRaiz=0.0;
            	 double d1=0.0;
            	 double d2=0.0;
            	 double nd1=0.0;
            	 double nd2=0.0;
            	 double ndp1=0.0;
            	 double delta=0.0;
            	 double gamma=0.0;
            	 double vega=0.0;
            	 double theta=0.0;
            	 Date fecha_proceso=null;
            	 Date fecha_vencimiento=null;
            	 Date fecha_t2_dias_proceso=null;
            	 Date fecha_t2_vencimiento=null;
            	 double DaysVto_Proceso=0;
            	 double DaysT2Proceso_Proceso=0;
            	 double DaysT2Vto_Proceso=0;
            	 double DaysT2Vto_T2Proces=0;
            	 double[] taza_DaysT2Proceso_Proceso= new double[2];
            	 double []taza_DaysT2Vto_Proceso = new double[2];
            	 long diff = 0;
            	 double Nominal = 0;
            	 double Posicion = 0;
            	 double auxiliar = 0;
            	 Spot = Double.parseDouble(infoMx3[19]);
            	 Days_to_Maturity = Double.parseDouble(infoMx3[21]);
            	 MarketVolume = Double.parseDouble(infoMx3[25]);
            	 Strike = Double.parseDouble(infoMx3[27]);
            	 Tasa_desc_div_base = Double.parseDouble(infoMx3[56]);
            	 Tasa_desc_div_sub = Double.parseDouble(infoMx3[57]);
            	 BUY_SELL = infoMx3[9];
            	 CALL_PUT = infoMx3[10];
            	 
            	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                 fecha_proceso = sdf.parse(fechaActual);
                 fecha_vencimiento = sdf.parse(infoMx3[22]);
                 diff = fecha_vencimiento.getTime() - fecha_proceso.getTime();
                 TimeUnit time = TimeUnit.DAYS; 
                 DaysVto_Proceso = time.convert(diff, TimeUnit.MILLISECONDS);// Dias al vencimiento 	
                 
                 Calendar fechaHoy = Calendar.getInstance();
                 Calendar fechaVencimiento = Calendar.getInstance();
                 Calendar fechaT2 = Calendar.getInstance();
                 Calendar fechaVencimientoT2 = Calendar.getInstance();
                 fechaHoy.setTime(fecha_proceso);
                 fechaVencimiento.setTime(fecha_vencimiento);
                 /*fechaT2.setTime(fecha_t2_dias_proceso);
                 fechaVencimientoT2.setTime(fecha_t2_vencimiento);*/
                 if(fechaHoy.get(Calendar.DAY_OF_WEEK)== Calendar.FRIDAY||fechaHoy.get(Calendar.DAY_OF_WEEK)== Calendar.THURSDAY) {
                	 fechaT2 = fechaHoy; 
                	 fechaT2.add(Calendar.DAY_OF_MONTH, 4);
                	 int dia = fechaT2.get(Calendar.DAY_OF_MONTH);
                	 int mes = fechaT2.get(Calendar.MONTH)+1;
                	 int anio= fechaT2.get(Calendar.YEAR);
                	 String FechaProcT2 = dia+"/"+mes+"/"+anio;
                	 fecha_t2_dias_proceso = sdf.parse(FechaProcT2);
                	 
                 }else {
                	 fechaT2 = fechaHoy; 
                	 fechaT2.add(Calendar.DAY_OF_MONTH, 2); 
                	 int dia = fechaT2.get(Calendar.DAY_OF_MONTH);
                	 int mes = fechaT2.get(Calendar.MONTH)+1;
                	 int anio= fechaT2.get(Calendar.YEAR);
                	 String FechaProcT2 = dia+"/"+mes+"/"+anio;
                	 fecha_t2_dias_proceso = sdf.parse(FechaProcT2);
                 }
                 if(fechaVencimiento.get(Calendar.DAY_OF_WEEK)== Calendar.FRIDAY||fechaVencimiento.get(Calendar.DAY_OF_WEEK)== Calendar.THURSDAY) {
                	 fechaVencimientoT2 = fechaVencimiento; 
                	 fechaVencimientoT2.add(Calendar.DAY_OF_MONTH, 4);
                	 int dia = fechaVencimientoT2.get(Calendar.DAY_OF_MONTH);
                	 int mes = fechaVencimientoT2.get(Calendar.MONTH)+1;
                	 int anio= fechaVencimientoT2.get(Calendar.YEAR);
                	 String FechaProcT2 = dia+"/"+mes+"/"+anio;
                	 fecha_t2_vencimiento = sdf.parse(FechaProcT2);
                	 
                 }else {
                	 fechaVencimientoT2 = fechaVencimiento; 
                	 fechaVencimientoT2.add(Calendar.DAY_OF_MONTH, 2); 
                	 int dia = fechaVencimientoT2.get(Calendar.DAY_OF_MONTH);
                	 int mes = fechaVencimientoT2.get(Calendar.MONTH)+1;
                	 int anio= fechaVencimientoT2.get(Calendar.YEAR);
                	 String FechaProcT2 = dia+"/"+mes+"/"+anio;
                	 fecha_t2_vencimiento = sdf.parse(FechaProcT2);
                 }
              // Dias t+2 - fecha vencimiento sirve para calculo R
                 diff = fecha_t2_dias_proceso.getTime() - fecha_proceso.getTime(); 
                 DaysT2Proceso_Proceso = time.convert(diff, TimeUnit.MILLISECONDS);	                 
              // Dias vto t+2 - fecha vencimiento sirve para calculo R  
                 diff = fecha_t2_vencimiento.getTime() - fecha_proceso.getTime();
                 DaysT2Vto_Proceso= time.convert(diff, TimeUnit.MILLISECONDS);
              // Dias vto t+2 - fecha t+2 proceso sirve para calculo R
                 diff = fecha_t2_vencimiento.getTime() - fecha_t2_dias_proceso.getTime();
                 DaysT2Vto_T2Proces = time.convert(diff, TimeUnit.MILLISECONDS);            	
             //  Dias cumplimiento = fecha de vencimiento - fecha
                 
                 
                 taza_DaysT2Proceso_Proceso = leerMx.interpolacion(DaysT2Proceso_Proceso, fecha);//resultado de lineal
            	 taza_DaysT2Vto_Proceso =leerMx.interpolacion(DaysT2Vto_Proceso, fecha);//resultado de linea
            	 //diferencia = fecha de vencimiento - fecha de proceso
            	 //Te = 
            	 Days_to_Maturity = Double.parseDouble(infoMx3[21]);

            	 Te = Days_to_Maturity/365;
    			 VolM = Double.parseDouble(infoMx3[26])/100;
    			 diasCumplimiento = DaysT2Vto_T2Proces;//Double.parseDouble(infoMx3[22]) - fecha_t2_dias_proceso;
    			 double[] datos = leerMx.interpolacion(diasCumplimiento, fecha);
    			 System.out.println(diasCumplimiento);
    			 Td = diasCumplimiento/365;
    			 Rd = (((1/(1+(taza_DaysT2Proceso_Proceso[0]*DaysT2Proceso_Proceso/360)))/(1/(1+(taza_DaysT2Vto_Proceso[0]*DaysT2Vto_Proceso/360))))-1)*360/DaysT2Vto_T2Proces;
    			 Rcs = (((1/(1+(taza_DaysT2Proceso_Proceso[1]*DaysT2Proceso_Proceso/360)))/(1/(1+(taza_DaysT2Vto_Proceso[1]*DaysT2Vto_Proceso/360))))-1)*360/DaysT2Vto_T2Proces;
    			 //Rd = datos[0] / 100;
    			 //Rcs = datos[1] /100;
    			
    			 Rdif = Rd - Rcs;
    			 FwdPrice = Spot*((1+(Rd*diasCumplimiento/360))/(1+(Rcs*diasCumplimiento/360)));
    			 LnFwdStr = Math.log(FwdPrice/Strike);
    			 VolTe = (Math.pow(VolM,2)/2)*Te;
    			 SumLnVol = LnFwdStr + VolTe;
    			 VolRaiz = VolM*(Math.sqrt(Te));
    			 d1 = SumLnVol/VolRaiz;
    			 d2 = d1-(VolM*(Math.sqrt(Te)));
				 nd = new NormalDistribution();
				 if(infoMx3[51].equals("COP")) {
					 Nominal = Double.parseDouble(infoMx3[52]);
				 }else {
					 Nominal = Double.parseDouble(infoMx3[50]);
				 }
				 if(BUY_SELL.equals("B")) {
					 Posicion = 1;
				 }else {
					 Posicion = -1;
				 }
				 if(CALL_PUT.equals("C")) {
					  auxiliar = -1;
					 nd1 = 1-(nd.cumulativeProbability(d1)); 
				 }else {
					  auxiliar = 1;
				 nd1 = nd.cumulativeProbability(d1);}
				 nd2 = nd.cumulativeProbability(d2);
				 ndp1 = Math.exp((-1*Math.pow(d1, 2))/2)/(Math.sqrt(2*PI));
				 delta = nd1 * Math.exp(-Rcs*Te)*100*Posicion*auxiliar;//positivo o negativo si es call o put 
				 gamma = ((Math.exp(-Rcs*Td)*ndp1)/(Spot*VolM*Math.sqrt(Td)))*Nominal*Spot/100 * Posicion;
				 vega = ((Spot*Math.sqrt(Te))*(Math.exp(-Rcs*Td))*(ndp1))*Nominal/100*Posicion;
				 //theta = (1/365)*((Spot*Math.exp(Td*Rcs)*ndp1*VolM/(2*Math.sqrt(Te)))-(Rd*Strike*Math.exp(-Rd*Td)*nd2)+(Rcs*Spot*Math.exp(-Rcs*Td)*nd1));
        	 System.out.println(delta+" "+gamma+" "+vega+" "+theta);
        	 //DatosD1=[spot,days to Maturity,Market Volume,Strike,Tasa_desc_div_base,Tasa_desc_div_sub}
        	 //DatosD2 = [days to Maturity, MarketVolume]
        	 //ND1=[PI,D1]
        	 //ND2=[PI,D2]
        	 //Delta=[BUY_SELL,CALL_PUT,D1]
        	 //Gamma=[Spot,Days_toMaturity.Market Volume,ND1]
           	 //Vega = [Spot,Days_to_Maturity,ND1]
           	 //Theta=[CALL_PUT,Spot,Days_to_Maturity,MarketVolume,Strike,Tasa_desc_div_base,Tasa_desc_div_sub,D2,ND1]
           	 //Rho = [CALL-PUT,Days_To_Maturity,Strike,Tasa_desc_div_base,Tasa_desc_div_sub,D2]
           	 String Gamma = String.format(Locale.ROOT,"%.4f", gamma); 
           	 String Vega = String.format(Locale.ROOT,"%.4f", vega); 
           	 String Delta = String.format(Locale.ROOT,"%.4f", delta); 
           	 comparacionMx3 = fields[0]+";"+fields[1]+";"+fields[2]+";"+fields[3]+";"+fields[4]+";"+fields[5]+";"+fields[6]+";"+Gamma+";"+Vega+";"+Delta;
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

