package com.sm.tempreader;
import org.json.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import  org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TempController {
    @RequestMapping(value="/temp")
    public List<String> getTemp()
    {
        ArrayList<String> result= new ArrayList<>() ;
try {
    RestTemplate template = new RestTemplate();

    String uriTemplate = "http://192.168.0.61/api/slot/0/io/rtd";
    URI uri = UriComponentsBuilder.fromUriString(uriTemplate).build().toUri();
    HttpHeaders headers=new HttpHeaders();
    headers.add("Accept", "vdn.dac.v1");

    RequestEntity<Void> requestEntity = RequestEntity.get(uri)
            .headers(headers)
            .build();

    ResponseEntity<String> response = template.exchange(requestEntity, String.class);

    String body = response.getBody();

    if (body == null) return result;

    var user = new JSONObject(body);

    var Io=user.getJSONObject("io");
    var Rtd=Io.getJSONArray("rtd");
    result.add("Outside: " + Rtd.getJSONObject(0).getDouble("rtdValueScaled"));
    result.add("BadRoomNearWindows: " + Rtd.getJSONObject(1).getDouble("rtdValueScaled"));
    result.add("BathRoom: " + Rtd.getJSONObject(2).getDouble("rtdValueScaled"));
    result.add("BadRoomNearDoor: " + Rtd.getJSONObject(3).getDouble("rtdValueScaled"));

    return result;
}catch (Exception ex)
{
    System.out.println(ex.getMessage());
}
        return result;
    }
}
