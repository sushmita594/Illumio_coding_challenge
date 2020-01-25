//This class provides equal and hash methods to compare hash of incoming packets and rules generated from csv to determine if packets match with the rules
public class Rules {
	 int port;
	 String direction;
     String protocol;
     String ipAddr;

    /**
     * constructor for building rules
     * @param direction
     * @param protocol
     * @param port
     * @param ipAddress
     */
    public Rules(String direction, String protocol, int port, String ipAddr) {
        this.direction = direction;
        this.protocol = protocol;
        this.port = port;
        this.ipAddr = ipAddr; 
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((ipAddr == null) ? 0 : ipAddr.hashCode());
		result = prime * result + port;
		result = prime * result + ((protocol == null) ? 0 : protocol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rules other = (Rules) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (ipAddr == null) {
			if (other.ipAddr != null)
				return false;
		} else if (!ipAddr.equals(other.ipAddr))
			return false;
		if (port != other.port)
			return false;
		if (protocol == null) {
			if (other.protocol != null)
				return false;
		} else if (!protocol.equals(other.protocol))
			return false;
		return true;
	}



	
    
}
