package com.cndatacom.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtils {
	//用户名 
    private static String userName = "admin"; 
    //密码 
    private static String passWord = "admin"; 
    //地址 
    private static String ip = "192.168.18.95"; 

    //上传文件存放的目录 
    private static String UPLOAD_DIR = "upload"; 
    
    //OA上传xml文件到MpmsFtp的目录 
    private static String DOWNLOAD_DIR = "test/download"; 

    //ftp客户端 
    private static FTPClient ftpClient = new FTPClient(); 
    
    /** 
     * 构造函数，当目录不存在的时候，创建文件夹 
     * @return 
     */ 
    public void FTPUtils() throws IOException{ 
    connectToServer(); 
    //判断指定路径是否存在，不存在就新建目录 
    checkPathExist("test"); 
    checkPathExist(UPLOAD_DIR); 
    checkPathExist(DOWNLOAD_DIR); 
    closeConnect(); 
    } 
    
    /** 
     * 查找指定目录是否存在 
     * @param String filePath 要查找的目录 
     * @return boolean:存在:true，不存在:false 
     * @throws IOException 
     */ 
    private static boolean checkPathExist(String filePath) throws IOException{ 
    boolean existFlag = false; 
    try{ 
    if(!ftpClient.changeWorkingDirectory(filePath)){ 
    ftpClient.makeDirectory(filePath); 
    } 
    }catch(Exception e){ 
    e.printStackTrace(); 
    } 
    return existFlag; 
    } 
    
    /** 
     * 连接到ftp服务器 
     */ 
    private static void connectToServer() { 
        if (!ftpClient.isConnected()) { 
            int reply; 
            try { 
                ftpClient=new FTPClient(); 
                ftpClient.connect(ip); 
                
                ftpClient.login(userName, passWord); 
                reply = ftpClient.getReplyCode(); 

                if (!FTPReply.isPositiveCompletion(reply)) { 
                    ftpClient.disconnect(); 
                    System.err.println("FTP server refused connection."); 
                } 
            } catch (Exception e) { 
                System.err.println("登录ftp服务器【"+ip+"】失败"); 
                e.printStackTrace(); 
            } 
        } 
    } 

    /** 
     * 关闭连接 
     */ 
    private static void closeConnect(){ 
        try{ 
            if(ftpClient!=null){ 
                ftpClient.logout(); 
                ftpClient.disconnect(); 
            } 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
    } 

    /** 
     * 转码[GBK ->  ISO-8859-1] 
     * 不同的平台需要不同的转码 
     * @param obj 
     * @return 
     */ 
    private static String gbkToIso8859(Object obj){ 
        try{ 
            if(obj==null) 
                return ""; 
            else 
                return new String(obj.toString().getBytes("GBK"),"iso-8859-1"); 
        }catch(Exception e){ 
            return ""; 
        } 
    } 

    /** 
     * 转码[ISO-8859-1 ->  GBK] 
     * 不同的平台需要不同的转码 
     * @param obj 
     * @return 
     */ 
    private static String iso8859ToGbk(Object obj){ 
        try{ 
            if(obj==null) 
                return ""; 
            else 
                return new String(obj.toString().getBytes("iso-8859-1"),"GBK"); 
        }catch(Exception e){ 
            return ""; 
        } 
    } 
    
    /** 
     * 设置传输文件的类型[文本文件或者二进制文件] 
     * @param fileType--BINARY_FILE_TYPE、ASCII_FILE_TYPE 
     */ 
    private static void setFileType(int fileType){ 
        try{ 
            ftpClient.setFileType(fileType); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
    } 

    /** 
     * 查找指定目录下面指定名称的文件是否存在 
     * @param String filePath 要查找的目录 
     * @param String fileName 要查找的文件名称 
     * @return boolean:存在:true，不存在:false 
     * @throws IOException 
     */ 
    private static boolean checkFileExist(String filePath, String fileName) throws IOException{ 
	    boolean existFlag = false; 
		//跳转到指定的文件目录 
		if(filePath!=null && !filePath.equals("")){ 
		if(filePath.indexOf("/")!=-1){ 
		int index = 0; 
		while((index=filePath.indexOf("/")) != -1){ 
		    ftpClient.changeWorkingDirectory(filePath.substring(0,index)); 
		    filePath = filePath.substring(index+1,filePath.length()); 
		} 
		if(!filePath.equals("")){ 
		ftpClient.changeWorkingDirectory(filePath); 
		} 
		}else{ 
		ftpClient.changeWorkingDirectory(filePath); 
		} 
		} 
	    String[] fileNames = ftpClient.listNames(); 
	    if(fileNames!=null && fileNames.length>0){ 
	    for(int i=0;i<fileNames.length;i++){ 
	    if(fileNames[i]!=null && iso8859ToGbk(fileNames[i]).equals(fileName)){ 
	    existFlag = true; 
	    break; 
	    } 
	    } 
    } 
    ftpClient.changeToParentDirectory(); 
    return existFlag; 
    } 
    
    /** 
     * 从ftp下载指定名称的文件到本地 
     * @param String remoteFileName --服务器上的文件名(只需要文件名，比如"req_0823.doc") 
     * @param String localFileName--本地文件名（包括完整的物理路径和文件名，比如"F:/ftpfile/req_0823.doc"，文件名可以自己定，可以不和服务器上的名字一致） 
     */ 
    private static boolean downloadFileByName(String remoteFilePath, String remoteFileName,String localFileName) throws IOException{ 
        boolean returnValue = false; 
    //下载文件 
        BufferedOutputStream buffOut=null; 
        try{ 
        //连接ftp服务器 
            connectToServer(); 
        File localFile = new File(localFileName.substring(0,localFileName.lastIndexOf("/"))); 
        if(!localFile.exists()){ 
        localFile.mkdirs(); 
        } 
        if(!checkFileExist(remoteFilePath, remoteFileName)){ 
        System.out.println("<----------- ERR : file  " + remoteFilePath + "/" + remoteFileName + " does not exist, download failed!----------->"); 
        return false; 
        } 
        else{ 
        //跳转到指定的文件目录 
        if(remoteFilePath!=null){ 
        if(remoteFilePath.indexOf("/")!=-1){ 
        int index = 0; 
        while((index=remoteFilePath.indexOf("/")) != -1){ 
        ftpClient.changeWorkingDirectory(remoteFilePath.substring(0,index)); 
        remoteFilePath = remoteFilePath.substring(index+1,remoteFilePath.length()); 
        } 
        if(!remoteFilePath.equals("")){ 
        ftpClient.changeWorkingDirectory(remoteFilePath); 
        } 
        }else{ 
        ftpClient.changeWorkingDirectory(remoteFilePath); 
        } 
        } 
            //设置传输二进制文件 
            setFileType(FTP.BINARY_FILE_TYPE); 
            //获得服务器文件 
            buffOut=new BufferedOutputStream(new FileOutputStream(localFileName)); 
            returnValue = ftpClient.retrieveFile(gbkToIso8859(remoteFileName), buffOut); 
            //输出操作结果信息 
            if(returnValue){ 
            System.out.println("<----------- INFO: download " + remoteFilePath + "/" + remoteFileName + " from ftp ： succeed! ----------->"); 
            }else{ 
            System.out.println("<----------- ERR : download " + remoteFilePath + "/" + remoteFileName + " from ftp : failed! ----------->"); 
            } 
        } 
            //关闭连接 
            closeConnect(); 
        }catch(Exception e){ 
            e.printStackTrace(); 
            returnValue = false; 
            //输出操作结果信息 
            System.out.println("<----------- ERR : download " + remoteFilePath + "/" + remoteFileName + " from ftp : failed! ----------->"); 
        }finally{ 
            try{ 
                if(buffOut!=null){ 
                    buffOut.close(); 
                } 
                if(ftpClient.isConnected()){ 
                    closeConnect(); 
                } 
            }catch(Exception e){ 
                e.printStackTrace(); 
            } 
        } 
        return returnValue; 
    } 

    //上传文件附件到ftp 
    public static boolean uploadAnnexToMpmsFtp(File uploadFile, String fileName,String path) throws IOException{ 
    boolean returnValue = false; 
    //上传文件 
        BufferedInputStream buffIn=null; 
        try{ 
        if(!uploadFile.exists()){ 
        System.out.println("<----------- ERR : annex named " + fileName + " not exist, upload failed! ----------->"); 
        return false; 
        }else{ 
        //建立连接 
        connectToServer(); 
            //设置传输二进制文件 
            setFileType(FTP.BINARY_FILE_TYPE); 
            //获得文件 
            buffIn=new BufferedInputStream(new FileInputStream(uploadFile)); 
            
            checkPathExist(path);
            
            //上传文件到ftp 
            returnValue = ftpClient.storeFile(gbkToIso8859(path + "/" + fileName), buffIn); 
            //输出操作结果信息 
            if(returnValue){ 
            System.out.println("<----------- INFO: upload file " + uploadFile.getAbsolutePath() + " to ftp : succeed! ----------->"); 
            }else{ 
            System.out.println("<----------- ERR : upload file " + uploadFile.getAbsolutePath() + " to ftp : failed! ----------->"); 
            } 
            //关闭连接 
            closeConnect(); 
        } 
        }catch(Exception e){ 
            e.printStackTrace(); 
            returnValue = false; 
            System.out.println("<----------- ERR : upload file " + uploadFile.getAbsolutePath() + " to ftp : failed! ----------->"); 
        }finally{ 
try{ 
    if(buffIn!=null){ 
        buffIn.close(); 
    } 
    if(ftpClient.isConnected()){ 
            closeConnect(); 
    } 
}catch(Exception e){ 
    e.printStackTrace(); 
} 
        } 
        return returnValue; 
    } 

    /** 
     * 删除服务器上文件 
     * @param fileDir 文件路径 
     * @param fileName 文件名称 
     * @throws IOException 
     */ 
    private static boolean delFile(String fileDir, String fileName) throws IOException{ 
    boolean returnValue = false; 
    try{ 
        //连接ftp服务器 
            connectToServer(); 
    //跳转到指定的文件目录 
    if(fileDir!=null){ 
    if(fileDir.indexOf("/")!=-1){ 
    int index = 0; 
    while((index=fileDir.indexOf("/")) != -1){ 
        ftpClient.changeWorkingDirectory(fileDir.substring(0,index)); 
        fileDir = fileDir.substring(index+1,fileDir.length()); 
    } 
    if(!fileDir.equals("")){ 
    ftpClient.changeWorkingDirectory(fileDir); 
    } 
    }else{ 
    ftpClient.changeWorkingDirectory(fileDir); 
    } 
    } 
            //设置传输二进制文件 
            setFileType(FTP.BINARY_FILE_TYPE); 
            //获得服务器文件 
            returnValue = ftpClient.deleteFile(fileName); 
            //关闭连接 
            closeConnect(); 
            //输出操作结果信息 
            if(returnValue){ 
            System.out.println("<----------- INFO: delete " + fileDir + "/" + fileName + " at ftp:succeed! ----------->"); 
            }else{ 
            System.out.println("<----------- ERR : delete " + fileDir + "/" + fileName + " at ftp:failed! ----------->"); 
            } 
        }catch(Exception e){ 
            e.printStackTrace(); 
            returnValue = false; 
            //输出操作结果信息 
        System.out.println("<----------- ERR : delete " + fileDir + "/" + fileName + " at ftp:failed! ----------->"); 
        }finally{ 
            try{ 
                if(ftpClient.isConnected()){ 
                    closeConnect(); 
                } 
            }catch(Exception e){ 
                e.printStackTrace(); 
            } 
        } 
        return returnValue; 
    } 
	
	

}
