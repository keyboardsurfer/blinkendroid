package org.cbase.blinkendroid.network.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import android.util.Log;

/**
 * A multicast sender thread WARNING: NSFW yet
 * 
 */
public class SenderThread extends Thread {

    private String message;
    private InetAddress group;

    public SenderThread(InetAddress grp, String msg) {
	message = msg;
	group = grp;
    }

    @Override
    public void run() {
	try {
	    int i = 0;
	    MulticastSocket s = new MulticastSocket(6789);
	    s.joinGroup(group);

	    while (true) {
		String msg = message + " " + i;

		DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg
			.length(), group, 6789);
		s.send(hi);
		i++;
		Thread.currentThread().sleep(1000);
	    }
	} catch (Exception e) {
	    Log.e("foo", "", e);
	}
    }

}
