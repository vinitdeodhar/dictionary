package com.dictionary;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;


public class  InitializeServlet extends HttpServlet {
	


	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	
		resp.setContentType("text/plain");
		
		JID jid = new JID("vadeodhar89@gmail.com");
        String msgBody1 = "Initialization done";
        com.google.appengine.api.xmpp.Message msg1 = new MessageBuilder()
            .withFromJid(new JID("dictionarychat@appspot.com"))
            .withRecipientJids(jid)
            .withBody(msgBody1)
            .build();

        boolean messageSent = false;
        XMPPService xmpp = XMPPServiceFactory.getXMPPService();
        xmpp.sendInvitation(jid,new JID("dictionarychat@appspot.com"));
        SendResponse status = xmpp.sendMessage(msg1);
        messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);
            
		resp.getWriter().println("Initialization attempted");
		
		
		
	}
}
