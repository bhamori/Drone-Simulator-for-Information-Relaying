//Botond Hamori

import java.util.ArrayList;
import java.*;

public class Information
{
	//variables used for calculations
	public int dividend;
	public int divisor;
	int i, j;
	public boolean createData = false;
	
	//Other variables
	public ArrayList wholeInfo = new ArrayList();

	public Information(int a, int b)
	{
		dividend = a;
		divisor = b;
	}
	
	public void createInfo()
	{
		//populate the information arrayList
		for(int k = 0; k < dividend; k++)
		{
			wholeInfo.add(k);
		}
	}
	
	//getters
	public ArrayList getWholeInfo()
	{
		return wholeInfo;
	}
	
	public boolean getCreateData()
	{
		return createData;
	}
	
	//setters
	public void setDividend(int packages)
	{
		this.dividend = packages;
	}
	
	public void setDivisor(int drones)
	{
		this.divisor = drones;
	}
	
	public void update()
	{
		this.createInfo();
	}
}