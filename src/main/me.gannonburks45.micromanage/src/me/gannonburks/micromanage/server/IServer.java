package src.me.gannonburks.micromanage.server;

public interface IServer {

	/*
	 * Method for grabbing instance of server object.
	 * 
	 * @return Instance of Server.
	 */
	default Server getServer()
	{
		return (Server)this;
	}
}
