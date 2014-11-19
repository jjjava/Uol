package uol;

import java.io.*;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Hudson Schumaker
 */
public class UolHttp {

    private StringBuffer content;
    private String arquivo = "";
    private String path = "http://e.i.uol.com.br/album/";

    public UolHttp() {
        content = new StringBuffer();
    }

    public void getFile() {
        Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return (new PasswordAuthentication("hudson.sales",
                        "michelin11".toCharArray()));
            }
        };
        Authenticator.setDefault(authenticator);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.28.128.15", 3128));
        for (int k = 1; k < 200; k++) {
            arquivo = "111002f1_gatas_f_";
            String s = null;
            try {
                if (k < 10) {
                    arquivo = arquivo + "00" + k + ".jpg";
                }
                if (k >= 10 && k < 100) {
                    arquivo = arquivo + "0" + k + ".jpg";
                }
                if (k >= 100) {
                    arquivo = arquivo + k + ".jpg";
                }
                System.out.println(path + arquivo);
                URLConnection conn = new URL(path + arquivo).openConnection(proxy);
                InputStream is = conn.getInputStream();
                FileOutputStream fos = new FileOutputStream("C:/queue/" + arquivo);
                int umByte = 0;
                while ((umByte = is.read()) != -1) {
                    fos.write(umByte);
                }
                is.close();
                fos.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}