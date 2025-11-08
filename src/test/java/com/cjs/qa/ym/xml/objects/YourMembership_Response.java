package com.cjs.qa.ym.xml.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "YourMembership_Response")
public class YourMembership_Response
{
    @XmlElement(name = "ErrCode")
    EventsAllSearch errCode;

    public EventsAllSearch getErrCode() {
        return errCode;
    }

    @XmlElement(name = "extendedErrorInfo")
    EventsAllSearch extendedErrorInfo;

    public EventsAllSearch getExtendedErrorInfo() {
        return extendedErrorInfo;
    }

    @XmlElement(name = "Events.All.Search")
    EventsAllSearch eventsAllSearch;

    public EventsAllSearch getEventsAllSearch()
    {
        return eventsAllSearch;
    }

    @XmlElement(name = "Events.Event.Get")
    EventsEventGet eventsEventGet;

    public EventsEventGet getEventsEventGet()
    {
        return eventsEventGet;
    }

    @XmlElement(name = "Sa.Events.Event.Registration.Get")
    SaEventsEventRegistrationGet saEventsEventRegistrationGet;

    public SaEventsEventRegistrationGet getSaEventsEventRegistrationGet()
    {
        return saEventsEventRegistrationGet;
    }
}