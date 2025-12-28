package io;

public interface Content {
    Writer createWriter(BothFormatter format);
    BothFormatter createFormatter();
}