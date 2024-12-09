package com.example.demo.C06Google;


import com.example.demo.C04Kakao.C02KakaoLoginController;
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
        log.info("GET /google/cal...");
    }

    @GetMapping("/cal/post")
    @ResponseBody
    public void post(
            @RequestParam("title") String title,
            @RequestParam("desc") String desc
    ){

        // https://gist.github.com/forsrc/fdd693686d2cbe7e609a364dfc9d97ee
        // https://mvnrepository.com/artifact/com.google.apis/google-api-services-calendar/v3-rev411-1.25.0

        log.info("GET /google/cal/post..."+title+","+desc);

        Event event = new Event()
                .setSummary("Google I/O 2015")
                .setLocation("800 Howard St., San Francisco, CA 94103")
                .setDescription("A chance to hear more about Google's developer products.");

        DateTime startDateTime = new DateTime("2024-08-28T09:00:00-07:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setStart(start);

        DateTime endDateTime = new DateTime("2024-08-28T17:00:00-07:00");
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

        //RESTTEMPLATE
        String calendarId = "primary";  // 또는 사용자의 특정 캘린더 ID
        String url = "https://www.googleapis.com/calendar/v3/calendars/primary/events";
       // https://calendar.google.com/calendar/embed?src=1f94a9a08dfdb514a425e7180619d87248f1f7de25315fdf894f6c8b74406194%40group.calendar.google.com&ctz=Asia%2FSeoul
        //HTTP 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        //HTTP 요청 파라미터
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("","");

        //HTTP 엔티티(헤더 + 파라미터)
        HttpEntity< MultiValueMap<String, String> > entity = new HttpEntity<>(params, headers);

        //HTTP 요청 후 응답받기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response);


    }


}