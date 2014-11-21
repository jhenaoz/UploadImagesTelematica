package Server;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Service.ServiceImages;
/**
 * Este es un servlet para manejar la peticion del cliente de subida de archivo
 * @author jhenaoz
 */
@SuppressWarnings("serial")
public class FileUploadHandler extends HttpServlet {
//    private String DIRECTORIO_SUBIDAS ="C:/Users/jhenaoz/Servers/webapps/ROOT/Resources";
    public static final String FOLDER_IMG ="uploads";
    public static final String APP ="ImagenesTelematica";
    
    public  String createDirectory(String directory){
    	 File theDir = new File(directory+File.separator+FOLDER_IMG);
    	  if (!theDir.exists()) {
    	    System.out.println("creating directory:.... ");
    	    try{
    	        if(theDir.mkdir()){
    	      	  System.err.println("ABSOLUTE PATH: "+theDir.getAbsolutePath());
    	      	  return theDir.getAbsolutePath() + File.separator + FOLDER_IMG;
    	        }
    	     } catch(SecurityException se){
    	    	 se.printStackTrace();
    	    	 return "";
    	     }        
    	  }else{
    		  return directory+File.separator+ FOLDER_IMG;
    	  }
		return "";
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //process only if its multipart content
    	String DIRECTORIO = createDirectory(this.getServletContext().getRealPath(""));
    	System.out.println("DIRECTORIO:" +DIRECTORIO);
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                	System.out.println("FILE NAME: " +item.getName());
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        System.out.println("NOMBREEEEE: "+name);
                        //Persistencia en Servidor, necesita ruta total (depende el SO)
                        item.write( new File(DIRECTORIO + File.separator + name ));
                        //Persistencia en BD, almacena solo ruta parcial
                        ServiceImages si = new ServiceImages();
                        si.addImage(FOLDER_IMG+ File.separator +name);
                    }
                }
               request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
