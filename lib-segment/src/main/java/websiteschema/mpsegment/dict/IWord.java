package websiteschema.mpsegment.dict;

public interface IWord {

    public int getLog2Freq();

    public int getDomainType();

    public long getOccuredCount(String s);

    public long getOccuredSum();

    public POSArray getPOSArray();

    public int[][] getWordPOSTable();

    public int getWordLength();

    public String getWordName();

    public void incOccuredCount(String s);

    public void setDomainType(int i);

    public void setOccuredCount(String s, int i);

    public void setOccuredSum(int i);

    public int getWordMaxPOS();

    public String toDBFString();

}