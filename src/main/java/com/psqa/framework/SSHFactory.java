package com.psqa.framework;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by david_him on 12/16/2016.
 */
public class SSHFactory {

    public SSHFactory(){

    }

    public Session connectToSSH(String host, int port) {

        Session session = null;

        try{
            JSch jsch=new JSch();
            session = jsch.getSession("dhim", host, port);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            //String passwd = JOptionPane.showInputDialog("Enter password");
            session.setPassword("JwMGwTMS5");
            session.connect(30000);   // making a connection with timeout.
        }
        catch(Exception e){
            System.out.println(e);
        }
        return session;
    }

    public String sendCommand(Session session, String command) {

        StringBuilder outputBuffer = new StringBuilder();
        String terminalOutput = null;

        try {
            Channel channel = session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            InputStream commandOutput = channel.getInputStream();
            channel.connect();

            int readByte = commandOutput.read();
            while(readByte != 0xffffffff)
            {
                outputBuffer.append((char)readByte);
                readByte = commandOutput.read();
            }
            channel.disconnect();
            session.disconnect();
            terminalOutput = outputBuffer.toString();
            System.out.println(terminalOutput);
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return terminalOutput;
    }
}
