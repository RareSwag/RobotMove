//Jaden Heinle
public class CommandQueue
{
	private class ListNode
	{
		String data;
		ListNode link;
		public ListNode(String aData, ListNode aLink)
		{
			data = aData;
			link = aLink;
		}
	}
	private ListNode head;
	private ListNode tail;
	public CommandQueue()
	{
		head=tail=null;
	}
	public void enqueue(String aData)
	{
		ListNode newNode = new ListNode(aData,null);
		if(head==null) {
			head = tail = newNode;
			return;
		}
		tail.link = newNode;
		tail = tail.link;
	}
	public String dequeue()
	{
		if(head==null)
			return null;
		String value = head.data;
		head = head.link;
		return value;
	}
}
