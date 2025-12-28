package io;

public class HtmlContent implements Content 
{

    @Override
    public Writer createWriter(BothFormatter format) 
    {
        return new HtmlWriter(format);
    }

    @Override
    public BothFormatter createFormatter() 
    {
        return new BothFormatter();
    }
}