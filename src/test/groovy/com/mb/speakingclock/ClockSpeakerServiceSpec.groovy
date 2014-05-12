package com.mb.speakingclock

import spock.lang.Specification


class ClockSpeakerServiceSpec extends Specification {

    def "can speak the time"() {

        given:
        ClockSpeakerService speaker = new ClockSpeakerService()

        expect:
        speaker.speak(digital) == english

        where:
        digital | english
        "01:00" | "one o'clock in the morning"
        "01:15" | "one fifteen in the morning"
        "02:00" | "two o'clock in the morning"
        "02:35" | "two thirty-five in the morning"
        "12:00" | "twelve o'clock in the afternoon"
        "13:00" | "one o'clock in the afternoon"
        "13:59" | "one fifty-nine in the afternoon"
        "23:00" | "eleven o'clock in the evening"
        "23:30" | "eleven thirty in the evening"
        "00:00" | "twelve o'clock in the morning"
    }
}
