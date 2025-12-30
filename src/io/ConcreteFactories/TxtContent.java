package io.ConcreteFactories;

import io.AbstractFactory.Content;
import io.AbstractProducts.Formatter;
import io.AbstractProducts.Writer;
import io.ConcreteProducts.TxtFormatter;
import io.ConcreteProducts.TxtWriter;

public class TxtContent implements Content
{

    @Override
    public Writer createWriter(Formatter format) 
    {
        return new TxtWriter(format);
    }

    @Override
    public Formatter createFormatter() 
    {
        return new TxtFormatter();
    }   
    
}
