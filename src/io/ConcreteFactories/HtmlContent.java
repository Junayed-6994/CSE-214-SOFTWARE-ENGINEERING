package io.ConcreteFactories;

import io.AbstractFactory.Content;
import io.AbstractProducts.Formatter;
import io.AbstractProducts.Writer;
import io.ConcreteProducts.HtmlFormatter;
import io.ConcreteProducts.HtmlWriter;

public class HtmlContent implements Content 
{

    @Override
    public Writer createWriter(Formatter format) 
    {
        return new HtmlWriter(format);
    }

    @Override
    public Formatter createFormatter() 
    {
        return new HtmlFormatter();
    }
}