package com.dale.xutils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test_IO {

	public static void main(String[] args) {
		String head = "语音消息";
		String content = "图片地址在E:\\daleProject\\CommonComponent\\app";

		byte[] sendHead = head.getBytes();
		byte[] sendBoby = content.getBytes();

		int sendHeadLength = sendHead.length;
		int sendBobyLength = sendBoby.length;

		byte[] header = new byte[4];
		header[0] = (byte) ((sendHeadLength >> 8) & 0xff);
		header[1] = (byte) (sendHeadLength & 0xff);
		header[2] = (byte) ((sendBobyLength >> 8) & 0xff);
		header[3] = (byte) (sendBobyLength & 0xff);

		System.out.println("\n发送包头长度：" + sendHeadLength + " \n包头内容:" + head + "  \n发送包体长度：" + sendBobyLength + " \n包体内容：" + content);

		int totalLength = sendHeadLength + sendBobyLength + 4;
		byte[] sendByte = new byte[totalLength];
		System.arraycopy(header, 0, sendByte, 0, 4);
		System.arraycopy(sendHead, 0, sendByte, 4, sendHeadLength);
		System.arraycopy(sendBoby, 0, sendByte, sendHeadLength + 4, sendBobyLength);



		System.out.println("****************读*********************");
		
		InputStream inputStream = new ByteArrayInputStream(sendByte); 
		DataInputStream reader = new DataInputStream(inputStream);
		
		try {
			byte[] readByteArray = new byte[4];
			reader.read(readByteArray);
			int receiveHeadLength = (short) ((short) (readByteArray[0] << 8) + (short) (readByteArray[1] & 0xff));
			int receiveBobyLength = (short) ((short) (readByteArray[2] << 8) + (short) (readByteArray[3] & 0xff));
			
			String readHead = null;
			String readBoby = null;
			
			if (receiveHeadLength > 0) {// 读报文头
				byte[] receiveHead = new byte[receiveHeadLength];
				byte[] buf = new byte[1024];
				int resdHeadLength = 0;
				int rl;
				while(resdHeadLength < receiveHeadLength) {
					int lastLength = receiveHeadLength - resdHeadLength;
					if (lastLength > 1024) {
						rl = reader.read(buf);
					} else {
						rl = reader.read(buf, 0, lastLength);
					}
					if (rl >= 0) {
					  System.arraycopy(buf, 0, receiveHead, resdHeadLength, rl);
					  resdHeadLength += rl;
					}
				}
				
				readHead = new String(receiveHead);
			}
			
	
			
			
			if (receiveBobyLength > 0) {// 读报文体
				byte[] receiveBoby = new byte[receiveBobyLength];
				byte[] buf = new byte[1024];
				int resdLength = 0;
				int rl;
				while(resdLength < receiveBobyLength) {
					int lastLength = receiveBobyLength - resdLength;
					if (lastLength > 1024) {
						rl = reader.read(buf);
					} else {
						rl = reader.read(buf, 0, lastLength);
					}
					if(rl>=0){
					   System.arraycopy(buf, 0, receiveBoby, resdLength, rl);
					   resdLength += rl;
					}
				}
				readBoby = new String(receiveBoby);
			}

			System.out.println("\n接收包头长度：" + receiveHeadLength + " \n包头内容:" + readHead + "  \n接收包体长度：" + receiveBobyLength + " \n包体内容：" + readBoby);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
