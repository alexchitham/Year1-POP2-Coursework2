import java.util.ArrayList;

public class CustomSort implements SortingInterface
{
	
	private ArrayList<Double> values;
	
	public CustomSort()
	{
		values = new ArrayList<Double>();
	}

	public void setValues(ArrayList<Double> values)
	{
		this.values = values;
		sort();
	}
	

	public ArrayList<Integer> getGaps()
	{
		ArrayList<Integer> gaps = new ArrayList<Integer>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		int n = (this.values).size();
		int gap = 1;
		int i = 2;
		
		while (gap < n)
		{
			temp.add(gap);
			gap = pow(2, i) - 1;
			i += 1;
		}
		
		for (i = (temp.size() - 1); i >= 0; i--)
		{
			gaps.add(temp.get(i));
		}
		return gaps;
	}
	
	
	public void sort()
	{
		int n = (this.values).size();
		ArrayList<Integer> gaps = new ArrayList<Integer>();
		gaps = getGaps();
		
		for (int gap : gaps)
		{
			for (int i = gap; i < n; i++)
			{
				double temp = values.get(i);
				
				int j = 0;
				for (j = i; j >= gap; j -= gap)
				{
					if (values.get(j - gap) <= temp)
					{
						break;
					}
					values.set(j, values.get(j - gap));
				}
				values.set(j, temp);
						
			}
		}
		
	}
	
	public void add(Double value)
	{
		if (value == null)
		{
			return;
		}
		(this.values).add(value);
		sort();
		
	}
	
	public void remove(int index)
	{
		if (index > values.size() || index < 0 || values.size() < 1)
		{
			return;
		}
		(this.values).remove(index);
	}
	
	
	
	public int pow(int base, int power)
	{
		int ans = 1;
		for (int i = 0; i < power; i++)
		{
			ans *= base;
		}
		return ans;
	}
	
	
	
	
	public static void main(String[] args) 
	{
		CustomSort list = new CustomSort();
		list.add(5.0);
		list.add(7.0);
		list.add(2.0);
		list.add(1.0);
		list.add(3.0);
		list.remove(3);
		list.add(3.1);
		

	}

}
