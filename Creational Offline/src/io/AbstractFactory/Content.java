package io.AbstractFactory;

import io.AbstractProducts.Formatter;
import io.AbstractProducts.Writer;

public interface Content {
    Writer createWriter(Formatter format);
    Formatter createFormatter();
}