import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;



public class Illumio_Firewall {
	public static void main(String args[]) {
		Illumio_Firewall fw = new Illumio_Firewall("/Users/Sushmita/Desktop/rules_test.csv");
		//test data
		boolean pack1 = fw.acceptPacket("inbound", "udp",53,"192.168.1.255");
		boolean pack2 = fw.acceptPacket("outbound", "tcp",104,"192.168.1.3");		
		System.out.println("Packet 1 status "+pack1);System.out.println("Packet 2 status "+pack2);
		
	}
	
	HashSet<Rules> fwrules = new HashSet<Rules>();
	
	public Illumio_Firewall(String csv_path) {
		BufferedReader b = null;
		String ln = "";
		String input_file = csv_path;
		    	
        try {
            b = new BufferedReader(new FileReader(input_file));
            ln=b.readLine();
            while ((ln = b.readLine()) != null) {
                String[] data = ln.split(",");
                InputData ip= new InputData();
                
                ///Case 1 : port is a range
                if(data[2].contains("-") && !(data[3].contains("-"))) {
                	String[] data_port = data[2].split("-");	
                	for(int i = Integer.parseInt(data_port[0]); i<=Integer.parseInt(data_port[1]);i++){
                		ip.direction = data[0];
                		ip.protocol = data[1];
                		ip.port = i;
                		ip.ipAddr = data[3];
                		Rules r1 = new Rules(ip.get_direction(),ip.get_protocol(),ip.get_port(),ip.get_ipAddr());
                    	fwrules.add(r1);
                		
                	}
                	
                }
              ///Case 2 : both port and ip address are a range
                if(data[2].contains("-") && data[3].contains("-")) {
                	String[] data_port = data[2].split("-");	
                	for(int i = Integer.parseInt(data_port[0]); i<=Integer.parseInt(data_port[1]);i++){                		
                		String[] ip_range = data[3].split("-");	
                    	String[] ip_start = ip_range[0].split("\\.");  
                	    String[] ip_end = ip_range[1].split("\\.");   
                	    
                	    double ip_start_fin = ip_to_double_start(ip_start);
                	    double ip_end_fin = ip_to_double_start(ip_end);	   
                	    
                	    for (double j=ip_start_fin;j<=ip_end_fin;j++) {
                    		ip.direction = data[0];
                    		ip.protocol = data[1];
                    		ip.port = i;
                    		ip.ipAddr = ip_convert((long)j);
                    		//System.out.println("Port is "+ip.get_port()+" Ip Address "+ip.get_ipAddr());
                    		Rules r4 = new Rules(ip.get_direction(),ip.get_protocol(),ip.get_port(),ip.get_ipAddr());
                        	fwrules.add(r4);                		
                    	}
                		
                	}
                	
                }
                
              ///Case 3 : port is not a range and ip address is not a range
                if(!(data[2].contains("-")) && !(data[3].contains("-"))) {                		
                	ip.direction = data[0];
                	ip.protocol = data[1];
                	ip.port = Integer.parseInt(data[2]);
                	ip.ipAddr = data[3];     		
                	Rules r3 = new Rules(ip.get_direction(),ip.get_protocol(),ip.get_port(),ip.get_ipAddr());
                	fwrules.add(r3);
                }
              ///Case 4 : port is not a range and ip address is a range
                if(!(data[2].contains("-")) && data[3].contains("-")) {
                	String[] ip_range = data[3].split("-");	
                	String[] ip_start = ip_range[0].split("\\.");  
            	    String[] ip_end = ip_range[1].split("\\.");   
            	    
            	    double ip_start_fin = ip_to_double_start(ip_start);
            	    double ip_end_fin = ip_to_double_start(ip_end);

            	    
            	    for (double i=ip_start_fin;i<=ip_end_fin;i++) {
                		ip.direction = data[0];
                		ip.protocol = data[1];
                		ip.port = Integer.parseInt(data[2]);
                		ip.ipAddr = ip_convert((long)i);
                		Rules r4 = new Rules(ip.get_direction(),ip.get_protocol(),ip.get_port(),ip.get_ipAddr());
                    	fwrules.add(r4);                		
                	}
                	                }             
                           
                          
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (b != null) {
                try {
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }       
				
	}
	
	public static double ip_to_double_start(String[] ip_start) {
		double ip_start1 = Long.parseLong(ip_start[0])*Math.pow(256,3);
	    double ip_start2 = Long.parseLong(ip_start[1])*Math.pow(256,2);
	    double ip_start3 = Long.parseLong(ip_start[2])*Math.pow(256,1);
	    double ip_start4 = Long.parseLong(ip_start[3]);
	    double ip_start_fin = ip_start1+ip_start2+ip_start3+ip_start4;
	    return ip_start_fin;}
	
	
	public static double ip_to_double_end(String[] ip_end) {
	    double ip_end1 = Long.parseLong(ip_end[0])*Math.pow(256,3);
	    double ip_end2 = Long.parseLong(ip_end[1])*Math.pow(256,2);
	    double ip_end3 = Long.parseLong(ip_end[2])*Math.pow(256,1);
	    double ip_end4 = Long.parseLong(ip_end[3]);
	    double ip_end_fin = ip_end1+ip_end2+ip_end3+ip_end4;
	    return ip_end_fin;}
	
	public boolean acceptPacket(String direction, String protocol, int port, String ipAddress) {
		Rules packet = new Rules(direction, protocol, port, ipAddress);
		if (fwrules.contains(packet)) {
			return true;
		}
		else {
			return false;
		}

	}
	
	public static String ip_convert(long ip_add) {
		StringBuilder ip = new StringBuilder(15);
		for (int i = 0; i < 4; i++) {
			ip.insert(0, Long.toString(ip_add & 0xff));
			if (i < 3) {
				ip.insert(0, '.');
			} ip_add = ip_add >> 8;
		}
		return ip.toString();
	}

}
