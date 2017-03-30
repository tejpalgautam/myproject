import java.io.*;
import java.net.*;
class Client2 {
public static void main(String args[]) throws IOException
{
Socket s = new Socket("localhost",888);
DataOutputStream dos = new DataOutputStream(s.getOutputStream()); //to send the data to the server
BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));//to read the data from server
BufferedReader kb = new BufferedReader (new InputStreamReader(System.in));//to read data from keyboard
String str,str1;
while(!(str=kb.readLine()).equals("exit"))
{
dos.writeBytes(str+"\n");//send to server
str1 = br.readLine();//receive from server
System.out.println(str1);
}
dos.close();
br.close();
kb.close();
s.close();
}
}