/*
supporting code for implementing a linked stack.
*/


package support;

public class LLObjectNode<T>
{
	protected T info;
	protected LLObjectNode link;
	
	public LLObjectNode(T info)
	{
		this.info = info;
		link = null;
	}
	
	public void setInfo(T info)
	{
		this.info = info;
	}
	
	public T getInfo()
	{    
		return info;
	}

	public void setLink(LLObjectNode<T> link)
	{
		this.link = link;
	}

	public LLObjectNode<T> getLink()
	{
		return link;
	}

	
}//end of class
