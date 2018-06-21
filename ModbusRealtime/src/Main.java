import de.re.easymodbus.modbusclient.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
 
public class Main implements StaticVariables{
	static boolean[] C1_InputStatus = null;
    static boolean[] C1_Coils = null;
    static int[] C1_HoldReg = null;
    static boolean[] C2_InputStatus = null;
    static boolean[] C2_Coils = null;
    static int[] C2_HoldReg = null;
    static boolean[] C3_InputStatus = null;
    static boolean[] C3_Coils = null;
    static int[] C3_HoldReg = null;
    static boolean[] C4_InputStatus = null;
    static boolean[] C4_Coils = null;
    static int[] C4_HoldReg = null;
    
    static String[] outputCell1 = new String[13];
    static String[] outputCell2 = new String[8];
    static String[] outputCell3 = new String[8];
    static String[] outputCell4 = new String[13];
    static String outputs1, outputs2, outputs3, outputs4 = "";
    
	public static void main(String[] args)
	{
		Date d= new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		//System.out.println(df.format(d));
		boolean IsWorked=false;
		/*ShowGUI SG = new ShowGUI();
		SG.ReDraw(new Color(255,255,0));
		try {
			Thread.sleep(1000);
		}
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
		SG.ReDraw(new Color(0,255,0));
		*/
		//ModbusClient modbusClient = new ModbusClient("127.0.0.1",502);
		ModbusClient modbusClient_Cell1 = new ModbusClient("192.168.2.10", 502);    //Ip-Address and Port of Modbus-TCP-Server
        ModbusClient modbusClient_Cell2 = new ModbusClient("192.168.1.20", 502);    //Ip-Address and Port of Modbus-TCP-Server
        ModbusClient modbusClient_Cell3 = new ModbusClient("192.168.1.30", 502);    //Ip-Address and Port of Modbus-TCP-Server
        ModbusClient modbusClient_Cell4 = new ModbusClient("192.168.2.40", 502);    //Ip-Address and Port of Modbus-TCP-Server
		try
		{
			//modbusClient.Connect();
			modbusClient_Cell1.Connect();
			//System.out.println("test1");
            modbusClient_Cell2.Connect();
            //System.out.println("test2");
            modbusClient_Cell3.Connect();
            //System.out.println("test3");
            modbusClient_Cell4.Connect();
            System.out.println("test4");
            IsWorked = true;
            PrintWriter writer1 = new PrintWriter("OutputData/MonitorCell1_"+df.format(d)+".txt", "UTF-8");
            writer1.println("Time,R1_front,R1_rear,C1_endIR,C1_startIR,Main,R1_fwd_motor,R1_bwd_motor,C1_motor,C1_working_int,R1_in_transit_int,R1_fwd_int,R1_bwd_int");
            writer1.flush();
            //System.out.println("test1");
            PrintWriter writer2 = new PrintWriter("OutputData/MonitorCell2_"+df.format(d)+".txt", "UTF-8");
            writer2.println("Time,C1_IR,M1_IR,Main,M1_motor,C2_motor,C2_working_int,M1_working_int");
            writer2.flush();
            PrintWriter writer3 = new PrintWriter("OutputData/MonitorCell3_"+df.format(d)+".txt", "UTF-8");
            writer3.println("Time,C2_IR,M2_IR,Main,M2_motor,C3_motor,C3_working_int,M2_working_int");
            writer3.flush();
            PrintWriter writer4 = new PrintWriter("OutputData/MonitorCell4_"+df.format(d)+".txt", "UTF-8");
            writer4.println("Time,R2_front,R2_rear,C4_endIR,C3_IR,Main,R2_fwd_motor,R2_bwd_motor,C4_motor,C4_working_int,R2_in_transit_int,R2_fwd_int,R2_bwd_int");
            writer4.flush();
            
            while(IsWorked) {
            	//modbusClient.WriteMultipleRegisters(11, ModbusClient.ConvertFloatToTwoRegisters((float) 123.56));
            	C1_InputStatus = modbusClient_Cell1.ReadDiscreteInputs(2, 5);  //Read 5 Input Status from address 2 on Cell 1
            	C1_Coils = modbusClient_Cell1.ReadCoils(2, 3);     //Read 3 Coil data from address 2 on Cell 1
            	C1_HoldReg = modbusClient_Cell1.ReadHoldingRegisters(2051, 10);  //Read 5 Holding Registers from address 2051 on Cell 1
            	C2_InputStatus = modbusClient_Cell2.ReadDiscreteInputs(3, 4);  //Read 5 Input Status from address 3 on Cell 2
            	C2_Coils = modbusClient_Cell2.ReadCoils(2, 3);     //Read 3 Coil data from address 2 on Cell 2
            	C2_HoldReg = modbusClient_Cell2.ReadHoldingRegisters(2051, 6);  //Read 5 Holding Registers from address 2051 on Cell 2
            	C3_InputStatus = modbusClient_Cell3.ReadDiscreteInputs(3, 4);  //Read 5 Input Status from address 3 on Cell 3
            	C3_Coils = modbusClient_Cell3.ReadCoils(2, 3);     //Read 3 Coil data from address 2 on Cell 3
            	C3_HoldReg = modbusClient_Cell3.ReadHoldingRegisters(2051, 6);  //Read 5 Holding Registers from address 2051 on Cell 3
            	C4_InputStatus = modbusClient_Cell4.ReadDiscreteInputs(2, 5);  //Read 5 Input Status from address 2 on Cell 4
            	C4_Coils = modbusClient_Cell4.ReadCoils(2, 3);     //Read 3 Coil data from address 2 on Cell 4
            	C4_HoldReg = modbusClient_Cell4.ReadHoldingRegisters(2051, 10);  //Read 5 Holding Registers from address 2051 on Cell 4
            	
            	Date dd = new Date();
            	SimpleDateFormat dft=new SimpleDateFormat("HH:mm:ss");
            	
            	WriteOutputString();
            
            	writer1.println(dft.format(dd)+outputs1);
            	writer1.flush();
            	writer2.println(dft.format(dd)+outputs2);
            	writer2.flush();
            	writer3.println(dft.format(dd)+outputs3);
            	writer3.flush();
            	writer4.println(dft.format(dd)+outputs4);
            	writer4.flush();
            }
            
            writer1.close();
            writer2.close();
            writer3.close();
            writer4.close();
            
            modbusClient_Cell1.Disconnect();
            modbusClient_Cell2.Disconnect();
            modbusClient_Cell3.Disconnect();
            modbusClient_Cell4.Disconnect();
		}
		catch (Exception e)
		{		
			//IsWorked = false;
		}	
	}
	
	private static void WriteOutputString()
    {
        // Output String Cell1
        int outputi = 1;
        outputs1 = "";
        for (int i = 0; i < C1_InputStatus.length; i++)
        {
            outputCell1[outputi] = Boolean.toString(C1_InputStatus[i]);
            outputi++;
            outputs1 = outputs1 + "," + Boolean.toString(C1_InputStatus[i]);
        }
        for (int i = 0; i < C1_Coils.length; i++)
        {
            outputCell1[outputi] = Boolean.toString(C1_Coils[i]);
            outputi++;
            outputs1 = outputs1 + "," + Boolean.toString(C1_Coils[i]);
        }
        for (int i = 0; i < C1_HoldReg.length; i = i + 2)
        {
            if (i != 2)
            {
                outputCell1[outputi] = Integer.toString(C1_HoldReg[i + 1] * 2 + C1_HoldReg[i]);
                outputi++;
                outputs1 = outputs1 + "," + Integer.toString(C1_HoldReg[i + 1] * 2 + C1_HoldReg[i]);
            }
            else { }
            //Console.WriteLine("Value of HoldingRegister " + (i) + " " + C1_HoldReg[i].ToString());
        }
        // Output String Cell2
        outputi = 1;
        outputs2 = "";
        for (int i = 0; i < C2_InputStatus.length; i++)
        {
            if (i != 2)
            {
                outputCell2[outputi] = Boolean.toString(C2_InputStatus[i]);
                outputi++;
                outputs2 = outputs2 + "," + Boolean.toString(C2_InputStatus[i]);
            }
        }
        for (int i = 0; i < C2_Coils.length; i++)
        {
            if (i != 1)
            {
                outputCell2[outputi] = Boolean.toString(C2_Coils[i]);
                outputi++;
                outputs2 = outputs2 + "," + Boolean.toString(C2_Coils[i]);
            }

        }
        for (int i = 0; i < C2_HoldReg.length; i = i + 2)
        {
            if (i != 2)
            {
                outputCell2[outputi] = Integer.toString(C2_HoldReg[i + 1] * 2 + C2_HoldReg[i]);
                outputi++;
                outputs2 = outputs2 + "," + Integer.toString(C2_HoldReg[i + 1] * 2 + C2_HoldReg[i]);
            }
        }
        // Output String Cell3
        outputi = 1;
        outputs3 = "";
        for (int i = 0; i < C3_InputStatus.length; i++)
        {
            if (i != 2)
            {
                outputCell3[outputi] = Boolean.toString(C3_InputStatus[i]);
                outputi++;
                outputs3 = outputs3 + "," + Boolean.toString(C3_InputStatus[i]);
            }
        }
        for (int i = 0; i < C3_Coils.length; i++)
        {
            if (i != 1)
            {
                outputCell3[outputi] = Boolean.toString(C3_Coils[i]);
                outputi++;
                outputs3 = outputs3 + "," + Boolean.toString(C3_Coils[i]);
            }

        }
        for (int i = 0; i < C3_HoldReg.length; i = i + 2)
        {
            if (i != 2)
            {
                outputCell3[outputi] = Integer.toString(C3_HoldReg[i + 1] * 2 + C3_HoldReg[i]);
                outputi++;
                outputs3 = outputs3 + "," + Integer.toString(C3_HoldReg[i + 1] * 2 + C3_HoldReg[i]);
            }
        }
        // Output String Cell4
        outputi = 1;
        outputs4 = "";
        for (int i = 0; i < C4_InputStatus.length; i++)
        {
            outputCell4[outputi] = Boolean.toString(C4_InputStatus[i]);
            outputi++;
            outputs4 = outputs4 + "," + Boolean.toString(C4_InputStatus[i]);
        }
        for (int i = 0; i < C4_Coils.length; i++)
        {
            outputCell4[outputi] = Boolean.toString(C4_Coils[i]);
            outputi++;
            outputs4 = outputs4 + "," + Boolean.toString(C4_Coils[i]);
            //Console.WriteLine("Value of Coil " + (2 + i) + " " + C1_Coils[i].ToString());
        }
        for (int i = 0; i < C4_HoldReg.length; i = i + 2)
        {
            if (i != 2)
            {
                outputCell4[outputi] = Integer.toString(C4_HoldReg[i + 1] * 2 + C4_HoldReg[i]);
                outputi++;
                outputs4 = outputs4 + "," + Integer.toString(C4_HoldReg[i + 1] * 2 + C4_HoldReg[i]);
            }
            else { }
            //Console.WriteLine("Value of HoldingRegister " + (i) + " " + C1_HoldReg[i].ToString());
        }
    }
}