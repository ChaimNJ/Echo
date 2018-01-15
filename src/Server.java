import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static class MyThread implements Runnable {
        private Socket client;

        public MyThread(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                Scanner scan = new Scanner(client.getInputStream());
                scan.useDelimiter("\n");
                PrintStream out = new PrintStream(client.getOutputStream());
                boolean flag = true;
                while (flag) {
                    if (scan.hasNext()) {
                        String str = scan.next().trim();
                        if ("byebye".equalsIgnoreCase(str)) {
                            out.println("BYEBYE!!");
                            flag = false;
                        }
                        out.println("ECHO:" + str);
                        out.flush();
                    }
                }
                out.close();
                scan.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        boolean flag = true;
        while (flag) {
            Socket client = server.accept();
            new Thread(new MyThread(client)).start();
        }
        server.close();
    }
}
