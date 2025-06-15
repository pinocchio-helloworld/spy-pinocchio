package com.example.librarysb.model;

public class Information {
    private int bookcounts;
    private int borrowcounts;
    private int returncounts;
    private int usercounts;
    public int getBookcounts()
    {
        return bookcounts;
    }
    public void setBookcounts(int bookcounts)
    {
        this.bookcounts=bookcounts;
    }
    public int getBorrowcounts(){
        return borrowcounts;
    }
    public void setBorrowcounts(int borrowcounts){
        this.borrowcounts=borrowcounts;
    }
    public int getReturncounts(){
        return returncounts;
    }
    public void setReturncounts(int returncounts)
    {
        this.returncounts=returncounts;
    }
    public int getUsercounts(){
        return usercounts;
    }
    public void setUsercounts(int usercounts)
    {
        this.usercounts=usercounts;
    }
}
