package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
 	Map<String, WebSocket.Out<String>> members = new HashMap<String, WebSocket.Out<String>>();

    public WebSocket<String> chat() {
		  return WebSocket.whenReady((in, out) -> {
		  		members.put(new String(new Double(Math.random()).toString()), out);
		        // For each event received on the socket,

		        in.onMessage((a) -> {
		        	 for(WebSocket.Out<String> channel: members.values()) {
		        	 	channel.write(a);
		        	 }
		        });
		  }); 
     }

}
