package com.mb.speakingclock

class ClockSpeakerService {

    private def numbersInEnglish = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', 'ten',
                    'eleven' ,'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']
    private def multiplesOfTenInEnglish = ['zero', 'ten', 'twenty', 'thirty', 'forty', 'fifty']


    String speak(String digital) {
        String hours = speakHours(digital)
        String minutes = speakMinutes(digital)
        String phase = speakPhaseOfDay(digital)

        return "$hours $minutes in the $phase"
    }


    private def speakHours(String digital) {
        def hour = parseHour(digital)
        def hour12h = calculate12HourClockFormat(hour)

        return numbersInEnglish.get(hour12h)
    }

    private def speakMinutes(String digital) {
        def minutes = parseMinutes(digital)
        switch (minutes) {
            case 0:
                return  "o'clock"
            case 1..19:
                return numbersInEnglish.get(minutes)
            default:
                return composeNumber(minutes)
        }
    }

    private def speakPhaseOfDay(String digital) {
        def hour = parseHour(digital)
        switch (hour) {
            case 0..11:
                return "morning"
            case 12..17:
                return "afternoon"
            default:
                return "evening"
        }
    }

    private def calculate12HourClockFormat(int hour) {
        switch (hour) {
            case 0:
                return 12;
            case 1..12:
                return hour;
            default:
                return hour - 12;
        }
    }

    private def parseHour(String digital) {
        def hour = digital.split(/:/)[0] as Integer
        return hour
    }

    private def parseMinutes(String digital) {
        def minutes = digital.split(/:/)[1] as Integer
        return minutes
    }

    private def composeNumber(int minutes) {
        int tens = minutes / 10
        def number = multiplesOfTenInEnglish.get(tens)
        int units = minutes % 10
        if (units) {
            number += "-" + numbersInEnglish.get(units)
        }
        return number
    }

}
