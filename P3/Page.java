import java.util.ArrayList;

public class Page
{
    
    private int size;
    private int maxsize;
    private int remainder;
    private ArrayList<Record> records;

    public Page(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 4;
        this.remainder = maxsize - size;
        records = new ArrayList<Record>();

    }
    
    public int getsize()
    {
        return size;
    }

    public void setsize(int size)
    {
        this.size = size;
    }

    public int getMaxsize()
    {
        return maxsize;
    }

    public void setMaxsize(int maxsize)
    {
        this.maxsize = maxsize;
    }

    public int getRemainder()
    {
        return remainder;
    }

    public void setRemainder(int remainder)
    {
        this.remainder = remainder;
    }

    public ArrayList<Record> getRecords()
    {
        return records;
    }

    public void setRecords(ArrayList<Record> records)
    {
        this.records = records;
    }
}