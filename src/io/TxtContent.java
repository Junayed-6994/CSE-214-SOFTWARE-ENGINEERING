package io;

public class TxtContent implements Content
{

    @Override
    public Writer createWriter(BothFormatter format) 
    {
        return new TxtWriter(format);
    }

    @Override
    public BothFormatter createFormatter() 
    {
        return new BothFormatter();
    }   
    
}
