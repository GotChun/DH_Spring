package com.example.demo.C06Google;


import com.example.demo.C05Naver.C01NaverLoginController;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
@Slf4j
@RequestMapping("/google")
public class C02GoogleCalendarController {

    @GetMapping("/cal")
    public void main(){
        log.info("GET /google/calendar.,,!!");
    }

    
    @GetMapping("/cal/post")
    @ResponseBody
    public void post(
            @RequestParam("title") String title,
            @RequestParam("desc") String desc
            
    ){
        log.info("GET /google 포스트 뭐시기");
        // Refer to the Java quickstart on how to setup the environment:
// https://developers.google.com/calendar/quickstart/java
// Change the scope to CalendarScopes.CALENDAR and delete any stored
// credentials.

        Event event = new Event()
                .setSummary("Google I/O 2015")
                .setLocation("800 Howard St., San Francisco, CA 94103")
                .setDescription("A chance to hear more about Google's developer products.");

        DateTime startDateTime = new DateTime("2015-05-28T09:00:00-07:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setStart(start);

        DateTime endDateTime = new DateTime("2015-05-28T17:00:00-07:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setEnd(end);

        String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
        event.setRecurrence(Arrays.asList(recurrence));

        EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("lpage@example.com"),
                new EventAttendee().setEmail("sbrin@example.com"),
        };
        event.setAttendees(Arrays.asList(attendees));

        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);


        //REST TEMPLATE

        String cal_id = "1f94a9a08dfdb514a425e7180619d87248f1f7de25315fdf894f6c8b74406194@group.calendar.google.com";
        String url = "https://www.googleapis.com/calendar/v3/calendars/"+cal_id+"/events";

        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();

        //HTTP 요청 파라미터
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

        //HTTP 엔티티(헤더_파라미터)
        HttpEntity< MultiValueMap<String,String> > entity = new HttpEntity<>(params,headers);
        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,entity,String.class);
        System.out.println(response.getBody());

        System.out.printf("Event created: %s\n", event.getHtmlLink());




    }
}
