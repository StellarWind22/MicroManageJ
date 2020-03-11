package src.me.gannonburks.micromanage.module;

public interface IModule {

	default Module getModule()
	{
		return (Module)this;
	}
}
