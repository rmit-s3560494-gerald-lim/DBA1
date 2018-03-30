public class Record
{

    //public char[] REGISTER_NAME = new char[14];
    public char[] BN_NAME = new char[200];
    public char[] BN_STATUS = new char[12];
    public char[] BN_REG_DT = new char[10];
    public char[] BN_CANCEL_DT = new char[10];
    public char[] BN_RENEW_DT = new char[10];
    public char[] BN_STATE_NUM = new char[10];
    public char[] BN_STATE_OF_REG = new char[3];
    public char[] BN_ABN = new char[20];
    //public Record test = null;


    public Record(char[] BN_NAME, char[] BN_STATUS, char[] BN_REG_DT, char[] BN_CANCEL_DT, char[] BN_RENEW_DT, char[] BN_STATE_NUM, char[] BN_STATE_OF_REG, char[] BN_ABN)
    {
        //this.REGISTER_NAME = REGISTER_NAME;
        this.BN_NAME = BN_ABN;
        this.BN_STATUS = BN_STATUS;
        this.BN_REG_DT = BN_REG_DT;
        this.BN_CANCEL_DT = BN_CANCEL_DT;
        this.BN_RENEW_DT = BN_RENEW_DT;
        this.BN_STATE_NUM = BN_STATE_NUM;
        this.BN_STATE_OF_REG = BN_STATE_OF_REG;
        this.BN_ABN = BN_ABN;
    }
    
	/*public char[] getREGISTER_NAME() {
		return REGISTER_NAME;
	}


	public void setREGISTER_NAME(char[] rEGISTER_NAME) {
		REGISTER_NAME = rEGISTER_NAME;
	}*/

	public char[] getBN_NAME() {
		return BN_NAME;
	}


	public void setBN_NAME(char[] bN_NAME) {
		BN_NAME = bN_NAME;
	}


	public char[] getBN_STATUS() {
		return BN_STATUS;
	}


	public void setBN_STATUS(char[] bN_STATUS) {
		BN_STATUS = bN_STATUS;
	}


	public char[] getBN_REG_DT() {
		return BN_REG_DT;
	}


	public void setBN_REG_DT(char[] bN_REG_DT) {
		BN_REG_DT = bN_REG_DT;
	}


	public char[] getBN_CANCEL_DT() {
		return BN_CANCEL_DT;
	}


	public void setBN_CANCEL_DT(char[] bN_CANCEL_DT) {
		BN_CANCEL_DT = bN_CANCEL_DT;
	}


	public char[] getBN_RENEW_DT() {
		return BN_RENEW_DT;
	}


	public void setBN_RENEW_DT(char[] bN_RENEW_DT) {
		BN_RENEW_DT = bN_RENEW_DT;
	}


	public char[] getBN_STATE_NUM() {
		return BN_STATE_NUM;
	}


	public void setBN_STATE_NUM(char[] bN_STATE_NUM) {
		BN_STATE_NUM = bN_STATE_NUM;
	}


	public char[] getBN_STATE_OF_REG() {
		return BN_STATE_OF_REG;
	}


	public void setBN_STATE_OF_REG(char[] bN_STATE_OF_REG) {
		BN_STATE_OF_REG = bN_STATE_OF_REG;
	}


	public char[] getBN_ABN() {
		return BN_ABN;
	}


	public void setBN_ABN(char[] bN_ABN) {
		BN_ABN = bN_ABN;
	}
	
	public String toString()
	{
		String r = new String();
		for(int i = 0; i < BN_NAME.length; i++)
		{
			char a = BN_NAME[i];
			r +=a;
		}
		return r;
	}

}

