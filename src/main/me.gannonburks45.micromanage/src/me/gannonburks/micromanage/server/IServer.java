package src.me.gannonburks.micromanage.server;

public interface IServer {

	//Method for grabbing instance of server object.
	default Server getServer()
	{
		return (Server)this;
	}
}
