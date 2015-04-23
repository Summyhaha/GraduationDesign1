package nju.summy.synsession.server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class SynServer implements Runnable{
	DataInputStream inputFromClient;
	DataOutputStream outputToClient;
	
	public SynServer() {
//		System.out.println("I");
	}
	
	public void run() {
//		System.out.println("I'm running!");
		try {
			ServerSocket serverSocket = new ServerSocket(8000);				
			Socket socket = serverSocket.accept();					
			
			inputFromClient = new DataInputStream(socket.getInputStream());
			outputToClient = new DataOutputStream(socket.getOutputStream());
			
//			while(true) {
				String s = inputFromClient.readUTF();
				System.out.println("recieved:" + s);
//			}
		}
		catch(IOException ex) {
			System.err.println(ex);
		}      
	}
}
