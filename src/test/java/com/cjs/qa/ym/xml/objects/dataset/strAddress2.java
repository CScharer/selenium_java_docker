package com.cjs.qa.ym.xml.objects.dataset;

import com.cjs.qa.utilities.Constants;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "strAddress2")
public class strAddress2
{
    @XmlAttribute(name = "ExportValue")
    protected String exportValue;
    public String getExportValue()
    {
        return exportValue;
    }

    @XmlElement(name = "Value")
    private String value;

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Export Value:" + getExportValue() + Constants.NEWLINE);
        stringBuilder.append("Address2:" + getValue() + Constants.NEWLINE);
        return stringBuilder.toString();
    }
}