package de.dhbwka.java.exams.snatChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SnatChatRoom {
    private String roomName;
    private ArrayList<SnatChatFrontend> frontends = new ArrayList<SnatChatFrontend>();

    public SnatChatRoom(String roomName) {
        this.roomName = roomName;
    }

    public void register(SnatChatFrontend s) {
        this.frontends.add(s);

        try(BufferedReader br = new BufferedReader(new FileReader(this.roomName + ".txt"))) {
            List<String> messages = new ArrayList<String>();

            while(br.ready()) {
                messages.add(Message.rot13(br.readLine()));
            }

            if(messages.size() > 10) {
                messages = messages.subList(messages.size() - 10, messages.size());
            }

            for(String msg : messages) {
                sendMessageNoLog(msg);
            }
        } catch (IOException e) {}
    }

    public void unregister(SnatChatFrontend s) {
        this.frontends.remove(s);
    }

    public void sendMessage(Message msg) {
        for(SnatChatFrontend scf : this.frontends) {
            scf.receiveMessage(msg);
        }

        logMessage(msg.getSender().getName() + ": " + msg.getText());
    }

    public void sendMessage(String text) {
        for(SnatChatFrontend scf : this.frontends) {
            scf.receiveMessage(text);
        }

        logMessage(text);
    }

    private void sendMessageNoLog(String text) {
        for(SnatChatFrontend scf : this.frontends) {
            scf.receiveMessage(text);
        }
    }

    private void logMessage(String msg) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.roomName + ".txt", true))) {
            bw.write(Message.rot13(msg));
            bw.newLine();
        } catch (IOException e) {}
    }

    public String getRoomName() {
        return roomName;
    }
}
