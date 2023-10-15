package feature.booking.controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/Notation/{memNo}")
public class BookWebsocket {
    private static final Set<Session> connectedSession = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen (@PathParam("memNo") Integer memNo, Session userSession ) throws IOException {
        connectedSession.add(userSession);
        String text = String.format("Session ID = %s, connected; memNo = %s", userSession.getId(), memNo); //%s為站位符號
        System.out.println(text);
    }

    @OnMessage
    public void onMessage(Session userSession, String memNo){
        String message = memNo;
        for (Session session : connectedSession){
            //這邊的邏輯應該是只推播給管理員，待確認
            if(session.isOpen()){
                session.getAsyncRemote().sendText(message);
            }
            System.out.println("取消預約通知：" + message);
        }
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason){
        connectedSession.remove(userSession);
        String text = String.format("session ID = %s, discoonected; close code = %d; reason phrase = %s",
                userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
        System.out.println(text);
    }
    @OnError
    public void onError(Session userSession, Throwable e){
        System.out.println("Error: " + e.toString());
    }
}