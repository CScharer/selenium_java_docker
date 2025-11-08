package com.cjs.qa.ym.xml.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cjs.qa.utilities.Constants;
import com.cjs.qa.ym.xml.objects.dataset.*;
//import com.cjs.qa.ym.xml.objects.dataset.strEmail;

@XmlRootElement(name = "DataSet")
public class DataSet
{
    @XmlElement(name = "strEmail")
    private strEmail email;

    public strEmail getEmail()
    {
        return email;
    }

    @XmlElement(name = "strAddress1")
    private strAddress1 address1;

    public strAddress1 getAddress1()
    {
        return address1;
    }

    @XmlElement(name = "strAddress2")
    private strAddress2 address2;

    public strAddress2 getAddress2()
    {
        return address2;
    }

    @XmlElement(name = "strCity")
    private strCity city;

    public strCity getCity()
    {
        return city;
    }

    @XmlElement(name = "strLocation")
    private strLocation location;

    public strLocation getLocation()
    {
        return location;
    }

    @XmlElement(name = "strPostalCode")
    private strPostalCode postalCode;

    public strPostalCode getPostalCode()
    {
        return postalCode;
    }

    @XmlElement(name = "strCountry")
    private strCountry country;

    public strCountry getCountry()
    {
        return country;
    }

    @XmlElement(name = "strPhone")
    private strPhone phone;

    public strPhone getPhone()
    {
        return phone;
    }

    @XmlElement(name = "strWorkTitle")
    private strWorkTitle workTitle;

    public strWorkTitle getWorkTitle()
    {
        return workTitle;
    }

    @XmlElement(name = "strEmployerName")
    private strEmployerName employerName;

    public strEmployerName getEmployerName()
    {
        return employerName;
    }

    @XmlElement(name = "Custom_JobLevel")
    private Custom_JobLevel custom_JobLevel;

    public Custom_JobLevel getCustom_JobLevel()
    {
        return custom_JobLevel;
    }

    @XmlElement(name = "Custom_JobFunction")
    private Custom_JobFunction custom_JobFunction;

    public Custom_JobFunction getCustom_JobFunction()
    {
        return custom_JobFunction;
    }

    @XmlElement(name = "Custom_MayHPEemailyouofferssupportdatesandeventnews")
    private Custom_MayHPEemailyouofferssupportdatesandeventnews custom_MayHPEemailyouofferssupportdatesandeventnews;

    public Custom_MayHPEemailyouofferssupportdatesandeventnews getCustom_MayHPEemailyouofferssupportdatesandeventnews()
    {
        return custom_MayHPEemailyouofferssupportdatesandeventnews;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Email:" + getEmail() + Constants.NEWLINE);
        stringBuilder.append("Address1:" + getAddress1() + Constants.NEWLINE);
        stringBuilder.append("Address2:" + getAddress2() + Constants.NEWLINE);
        stringBuilder.append("City:" + getCity() + Constants.NEWLINE);
        stringBuilder.append("Location:" + getLocation() + Constants.NEWLINE);
        stringBuilder.append("Postal Code:" + getPostalCode() + Constants.NEWLINE);
        stringBuilder.append("Country:" + getCountry() + Constants.NEWLINE);
        stringBuilder.append("Phone:" + getPhone() + Constants.NEWLINE);
        stringBuilder.append("Work Title:" + getWorkTitle() + Constants.NEWLINE);
        stringBuilder.append("Employer Name:" + getEmployerName() + Constants.NEWLINE);
        stringBuilder.append("Custom_Job Level:" + getCustom_JobLevel() + Constants.NEWLINE);
        stringBuilder.append("Custom_Job Function:" + getCustom_JobFunction() + Constants.NEWLINE);
        stringBuilder.append("Custom_May HPE emailyouofferssupportdatesandeventnews:"
                + getCustom_MayHPEemailyouofferssupportdatesandeventnews() + Constants.NEWLINE);
        return stringBuilder.toString();
    }
}