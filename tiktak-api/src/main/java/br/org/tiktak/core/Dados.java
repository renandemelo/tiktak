package br.org.tiktak.core;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Dados {
	UUID uuid;
	String usuario;
	String evento;

	public String getMacAddress(){
		InetAddress ip;
		StringBuilder sb = new StringBuilder();
		String result = "";
		try {
			ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
			//System.out.println(sb.toString());
			result = sb.toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String getTimeStamp(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
		// System.out.println(dateFormat.format(date));
	}
	
	public Dados(String usuario, String evento){
		//UUID uuid = UUID.randomUUID();
		String mac = getMacAddress();
		String time = getTimeStamp();
		UUID uuid = UUID.nameUUIDFromBytes((mac + time + usuario + evento).getBytes());
		
		this.uuid = uuid;
		this.usuario = usuario;
		this.evento = evento;
	}
}
