package f;

public class My {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(myFun(4,true));
	}
	public static int myFun(int x,boolean z)
	{
		if(z)
		x=x+4;
		return x+3;
		
	}

}