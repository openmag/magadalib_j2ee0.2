package com.anheinno.magadapter.lib;

public interface IMAGHandler
{
    boolean process(MAGRequest req);

    String getAction();
}
