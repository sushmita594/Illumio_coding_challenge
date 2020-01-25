
public class InputData {
	//this class provides getter and setter methods for variables from the input rules csv
	
	String protocol;
	String ipAddr;
	String direction;
	int port;
	
		
	public String get_protocol() {
		return protocol;
	}
	public void set_protocol(String protocol) {
		this.protocol = protocol;
	}	
	public String get_ipAddr() {
		return ipAddr;
	}
	public void set_ipAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String get_direction() {
		return direction;
	}
	public void set_direction(String direction) {
		this.direction = direction;
	}
	public int get_port() {
		return port;
	}
	public void set_port(int port) {
		this.port = port;
	}	
}
