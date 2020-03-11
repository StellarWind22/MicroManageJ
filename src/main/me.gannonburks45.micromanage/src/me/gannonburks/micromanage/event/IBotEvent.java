package src.me.gannonburks.micromanage.event;

public interface IBotEvent {

	default BotEvent getBotEvent()
	{
		return (BotEvent)this;
	}
}
