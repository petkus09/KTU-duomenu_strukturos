package GUI;

import java.awt.Color;
import javax.swing.JTextArea;

//Klasė, skirta duomenų išvedimui į GUI
public class KsSwing {

    private static int lineNr;
    private static boolean formatStartOfLine = true;

    private static String getStartOfLine() {
        return (formatStartOfLine) ? ++lineNr + "| " : "";
    }

    public static void setFormatStartOfLine(boolean bool) {
        formatStartOfLine = bool;
    }

    public static void oun(JTextArea ta, Object o) {
        if (o instanceof Iterable) {
            for (Object iter : (Iterable) o) {
                ta.append(getStartOfLine() + iter.toString() + "\n");
            }
        } else {
            ta.append(getStartOfLine() + o.toString() + "\n");
        }
    }

    public static void ou(JTextArea ta, Object o, String msg) {
        ta.append(getStartOfLine() + msg + ": ");
        boolean bool = formatStartOfLine;
        formatStartOfLine = false;
        oun(ta, o);
        formatStartOfLine = bool;
    }

    public static void oun(JTextArea ta, Object o, String msg) {
        ta.append(getStartOfLine() + msg + ": " + "\n");
        boolean bool = formatStartOfLine;
        formatStartOfLine = false;
        oun(ta, o);
        formatStartOfLine = bool;
    }

    public static void ounerr(JTextArea ta, Exception e) {
        ta.setBackground(Color.pink);
        ta.append(getStartOfLine() + e.toString() + "\n");
        for (StackTraceElement ste : e.getStackTrace()) {
            ta.append(getStartOfLine() + ste.toString() + "\n");
        }
        ta.append("\n");
    }

    public static void ounerr(JTextArea ta, String msg) {
        ta.setBackground(Color.pink);
        ta.append(getStartOfLine() + msg + ". " + "\n");
    }

    public static void ounerr(JTextArea ta, String msg, String parameter) {
        ta.setBackground(Color.pink);
        ta.append(getStartOfLine() + msg + ": " + parameter + "\n");
    }

    //Nuosava situacija, panaudota dialogo struktūrose įvedamų parametrų tikrinimui.
    public static class MyException extends Exception {

        //Situacijos apibūdinimas
        private String message;
        //Situacijos kodas. Pagal jį programuojama programos reakcija į situaciją
        private int code;

        public MyException(String text) {
            // (-1) - susitariama, kad tai neutralus kodas.
            this(text, -1);
        }

        public MyException(String message, int code) {
            super(message);
            if (code < -1) {
                throw new IllegalArgumentException("Illegal code in MyException: " + code);
            }
            this.message = message;
            this.code = code;
        }

        @Override
        public String getMessage() {
            return message;
        }

        public int getCode() {
            return code;
        }
    }
}