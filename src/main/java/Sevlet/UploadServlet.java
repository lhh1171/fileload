package Sevlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.Enumeration;
import java.util.Random;


/**
 * @author lqc
 */

public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        System.out.print(req.getHeaderNames());
        Enumeration<?> enum1 = req.getHeaderNames();
        String boundary="";
        while (enum1.hasMoreElements()) {
            String key = (String) enum1.nextElement();
            String value = req.getHeader(key);
            if ("content-type".equals(key)){
                boundary=  value.split(";")[1].split("=")[1];
            }
            System.out.println(key + "\t" + value);
        }
        System.out.println("boundary is  "+boundary);
        String inputLine="";
        StringBuilder str = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            str.append(inputLine);
        }
        reader.close();
        String[] bodys=str.toString().split(boundary);
        String[] bodys1= bodys[2].split("Content-Type")[0].split("=");
        String ilename= bodys1[bodys1.length-1];
        String filename=ilename.substring(1,ilename.length()-1);
        String filetype=filename.split("\\.")[1];
        String bodys2=bodys[2].split("/"+filetype)[1];
        System.out.println("filetype is "+filetype);
        System.out.println("filename is "+ filename);
        File f=new File("/opt/fff/"+filename);
        OutputStream outputStream= new FileOutputStream(f);
        outputStream.write(bodys2.getBytes());
        System.out.println("");
        //        System.out.println("文件上传过来了");
//        InputStream inputStream=req.getInputStream();
//        byte[] buffer=new byte[10240000];
//        int read= inputStream.read(buffer);
//        String s=new String(buffer,0,read);
//        String ss=req.getContentType();
//        String boundary=ss.split("=")[1];
//        String[] strings = s.split(boundary);
//        String[] strings1 = strings[2].split("\n\r\n");
//        String body=strings1[1].split("\n\n")[0];
//        System.out.println("body is\n"+body);
//
//        String[] propertis=strings1[0].split(";");
//        String filenamee=propertis[propertis.length - 1].split("=")[1].split("\r\n")[0];
//        String filename=filenamee.substring(1,filenamee.length()-1);
//        String filetype=filename.split("\\.")[1];
//        System.out.println("proper is\n"+propertis);
//        System.out.println("filetype is \n"+filetype);
//        Random random=new Random();
//        File f=new File("/opt/fff/"+random.nextLong()+"."+filetype );
//        OutputStream outputStream= new FileOutputStream(f);
//        outputStream.write(body.getBytes());
//        String sss=req.getAuthType();
////        String[] ss= req.getContentType().split();
//        System.out.println("类型是1"+ss+"\n类型是2"+sss);
//        System.out.println(s);
//        Base64.Decoder decoder = Base64.getDecoder();
//        Base64.Encoder encoder = Base64.getEncoder();
//        BASE64Decoder base64Decoder=new BASE64Decoder();
//        BASE64Encoder base64Encoder=new BASE64Encoder();
//        byte[] bytes2 = encoder.encode(s.getBytes());
//        String s2=new String(bytes2,"UTF-8");
//        byte[] bytes = decoder.decode(bytes2);
//        String s1 = new String(bytes,"UTF-8");
//        System.out.println(s1);
//        System.out.println(s2);
//        if (ServletFileUpload.isMultipartContent(req)) {
//            //创建工厂实现类
//            FileItemFactory fileItemFactory = new DiskFileItemFactory();
//            //创建用于解析上传数据的工具类serverupload
//            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
//            //解析上传的数据
//            try {
//                List<FileItem> list = servletFileUpload.parseRequest(req);
//                for (FileItem fileItem : list) {
//                    if (fileItem.isFormField()) {
//                        //普通表单项
//                        System.out.println("name:   " + fileItem.getFieldName());
//                        System.out.println("value:  " + fileItem.getString("UTF-8"));
//                    } else {
//                        //上传的文件
//                        System.out.println("name:   " + fileItem.getFieldName());
//                        System.out.println("filename:   " + fileItem.getName());
//                        try {
//                            fileItem.write(new File("/opt/fff/" + fileItem.getName()));
//                            System.out.println("完成");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            } catch (FileUploadException e) {
//                e.printStackTrace();
//            }
//        }
    }
    //用来处理文件上传的数据
}
