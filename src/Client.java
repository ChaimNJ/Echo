import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException{
        Socket client = new Socket("127.0.0.1",8888);
        Scanner keyscan = new Scanner(System.in);
        keyscan.useDelimiter("\n");
        Scanner netscan = new Scanner(client.getInputStream());
        netscan.useDelimiter("\n");
        PrintStream netout = new PrintStream(client.getOutputStream());
        boolean flag = true;
        while(flag){
            System.out.println("输入信息：");
            if(keyscan.hasNext()){
                String str = keyscan.next().trim();
                netout.println(str);
                if(netscan.hasNext()){
                    System.out.println(netscan.next());
                }
                if("byebye".equalsIgnoreCase(str)){
                    flag = false;
                }
            }
        }
        keyscan.close();
        netscan.close();
        netout.close();

    }
}
