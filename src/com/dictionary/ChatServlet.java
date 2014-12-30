package com.dictionary;

import java.io.IOException;
import javax.servlet.http.*;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

public class ChatServlet {

	 public void doPost(HttpServletRequest req, HttpServletResponse res)
	          throws IOException {
	        XMPPService xmpp = XMPPServiceFactory.getXMPPService();
	        Message message = xmpp.parseMessage(req);

	        JID fromJid = message.getFromJid();
	        String body = message.getBody();
	        
	        String response = "you said "+body;
	        
	        com.google.appengine.api.xmpp.Message msg1 = new MessageBuilder()
            .withFromJid(new JID("dictionarychat@appspot.com"))
            .withRecipientJids(fromJid)
            .withBody(response)
            .build();

        boolean messageSent = false;
        
        SendResponse status = xmpp.sendMessage(msg1);
        messageSent = (status.getStatusMap().get(fromJid) == SendResponse.Status.SUCCESS);
	 }
}
