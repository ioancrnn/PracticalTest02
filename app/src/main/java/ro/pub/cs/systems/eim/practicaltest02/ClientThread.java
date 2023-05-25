package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private String address;
    private int port;
    private String valuteType;
    private TextView valutaInformationTextView;

    private Socket socket;

    public ClientThread(String clientAddress, int port, String valuteType, TextView valutaInformationEditText) {

        this.address = clientAddress;
        this.port = port;
        this.valuteType = valuteType;
        this.valutaInformationTextView = valutaInformationEditText;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);

            BufferedReader reader = Utilities.getReader(socket);
            PrintWriter writer = Utilities.getWriter(socket);

            writer.write(valuteType + "\n");
            writer.flush();

            String valutaInformation = reader.readLine();

            valutaInformationTextView.post(() -> valutaInformationTextView.setText(valutaInformation));

        } catch (IOException ioException) {
            Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        } finally {
            if (socket != null) {
                try {
                    // closes the socket regardless of errors or not
                    socket.close();
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                    if (Constants.DEBUG) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }
}