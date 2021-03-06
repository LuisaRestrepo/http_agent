package httpagent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

public class HttpRequestedPath {
    
    private String fileRequested;
    private String method;
    private static final File MY_DIR = new File(".");
    private static final File WEB_ROOT = new File(MY_DIR.getAbsolutePath()+"/src/repository/");
    private static final String DEFAULT_FILE = "index.html";
    private static final String FILE_NOT_FOUND = "404.html";
    private static final String METHOD_NOT_SUPPORTED = "not-supported.html";

    public HttpRequestedPath() {

    }

    public String getFileRequested() {
        return fileRequested;
    }

    public void setFileRequested(String fileRequested) {
        this.fileRequested = fileRequested;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    private static byte[] readFileData(File file, int fileLength) throws IOException {
		FileInputStream fileIn = null;
		byte[] fileData = new byte[fileLength];
		
		try {
			fileIn = new FileInputStream(file);
			fileIn.read(fileData);
		} finally {
			if (fileIn != null) 
				fileIn.close();
		}
		
		return fileData;
	}
	
	// return supported MIME Types
	public static String getContentType(String fileRequested) {
		if (fileRequested.endsWith(".htm")  ||  
                        fileRequested.endsWith(".html"))
			return "text/html";
		else
			return "text/plain";
	}
	
	public static byte[] fileNotFound(String fileRequested) 
                throws IOException {
		File file = new File(WEB_ROOT, FILE_NOT_FOUND);
		int fileLength = (int) file.length();
		
		byte[] fileData = readFileData(file, fileLength);
            
                return fileData;
		
	}
        
        public static void fileFound(PrintWriter out, OutputStream dataOut, 
                String fileRequested) throws IOException {
		File file = new File(WEB_ROOT, DEFAULT_FILE);
		int fileLength = (int) file.length();
		String content = "text/html";
		byte[] fileData = readFileData(file, fileLength);
		
		out.println("HTTP/1.1 200 OK");
		out.println("Server: Java HTTP Agent Server: 1.0");
		out.println("Date: " + new Date());
		out.println("Content-type: " + content);
		out.println("Content-length: " + fileLength);
		out.println(); // blank line between headers and content, very important !
		out.flush(); // flush character output stream buffer
		
		dataOut.write(fileData, 0, fileLength);
		dataOut.flush();
                 // we close socket connection
			
	}
    
    
    
}
