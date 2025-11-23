package utilities;

import pages.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriterUtil {

    private static final String HEADER = "username,password,firstName,lastName,email,phone,address1,address2,city,state,zip,country";

    public static void appendUserToCSV(String filePath, UserData user) {

        try {

            File file = new File(filePath);
            boolean fileExists = file.exists();

            try (FileWriter fw = new FileWriter(file,true)) {
                if (!fileExists) {
                    fw.write(HEADER + System.lineSeparator());
                }
                String line = String.join(",",
                        escape(user.username),
                        escape(user.password),
                        escape(user.firstName),
                        escape(user.lastName),
                        escape(user.email),
                        escape(user.phone),
                        escape(user.address1),
                        escape(user.address2),
                        escape(user.city),
                        escape(user.state),
                        escape(user.zip),
                        escape(user.country)
                );
                fw.write(line + System.lineSeparator());
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String escape(String value) {
        if (value == null) return "";
        // Simple escape for commas / newlines: wrap in quotes if contains comma or newline or quote
        if (value.contains(",") || value.contains("\n") || value.contains("\"")) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }
        return value;
    }
}
