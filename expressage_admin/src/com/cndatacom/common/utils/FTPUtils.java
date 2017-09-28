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
	//�û��� 
    private static String userName = "admin"; 
    //���� 
    private static String passWord = "admin"; 
    //��ַ 
    private static String ip = "192.168.18.95"; 

    //�ϴ��ļ���ŵ�Ŀ¼ 
    private static String UPLOAD_DIR = "upload"; 
    
    //OA�ϴ�xml�ļ���MpmsFtp��Ŀ¼ 
    private static String DOWNLOAD_DIR = "test/download"; 

    //ftp�ͻ��� 
    private static FTPClient ftpClient = new FTPClient(); 
    
    /** 
     * ���캯������Ŀ¼�����ڵ�ʱ�򣬴����ļ��� 
     * @return 
     */ 
    public void FTPUtils() throws IOException{ 
    connectToServer(); 
    //�ж�ָ��·���Ƿ���ڣ������ھ��½�Ŀ¼ 
    checkPathExist("test"); 
    checkPathExist(UPLOAD_DIR); 
    checkPathExist(DOWNLOAD_DIR); 
    closeConnect(); 
    } 
    
    /** 
     * ����ָ��Ŀ¼�Ƿ���� 
     * @param String filePath Ҫ���ҵ�Ŀ¼ 
     * @return boolean:����:true��������:false 
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
     * ���ӵ�ftp������ 
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
                System.err.println("��¼ftp��������"+ip+"��ʧ��"); 
                e.printStackTrace(); 
            } 
        } 
    } 

    /** 
     * �ر����� 
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
     * ת��[GBK ->  ISO-8859-1] 
     * ��ͬ��ƽ̨��Ҫ��ͬ��ת�� 
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
     * ת��[ISO-8859-1 ->  GBK] 
     * ��ͬ��ƽ̨��Ҫ��ͬ��ת�� 
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
     * ���ô����ļ�������[�ı��ļ����߶������ļ�] 
     * @param fileType--BINARY_FILE_TYPE��ASCII_FILE_TYPE 
     */ 
    private static void setFileType(int fileType){ 
        try{ 
            ftpClient.setFileType(fileType); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
    } 

    /** 
     * ����ָ��Ŀ¼����ָ�����Ƶ��ļ��Ƿ���� 
     * @param String filePath Ҫ���ҵ�Ŀ¼ 
     * @param String fileName Ҫ���ҵ��ļ����� 
     * @return boolean:����:true��������:false 
     * @throws IOException 
     */ 
    private static boolean checkFileExist(String filePath, String fileName) throws IOException{ 
	    boolean existFlag = false; 
		//��ת��ָ�����ļ�Ŀ¼ 
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
     * ��ftp����ָ�����Ƶ��ļ������� 
     * @param String remoteFileName --�������ϵ��ļ���(ֻ��Ҫ�ļ���������"req_0823.doc") 
     * @param String localFileName--�����ļ�������������������·�����ļ���������"F:/ftpfile/req_0823.doc"���ļ��������Լ��������Բ��ͷ������ϵ�����һ�£� 
     */ 
    private static boolean downloadFileByName(String remoteFilePath, String remoteFileName,String localFileName) throws IOException{ 
        boolean returnValue = false; 
    //�����ļ� 
        BufferedOutputStream buffOut=null; 
        try{ 
        //����ftp������ 
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
        //��ת��ָ�����ļ�Ŀ¼ 
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
            //���ô���������ļ� 
            setFileType(FTP.BINARY_FILE_TYPE); 
            //��÷������ļ� 
            buffOut=new BufferedOutputStream(new FileOutputStream(localFileName)); 
            returnValue = ftpClient.retrieveFile(gbkToIso8859(remoteFileName), buffOut); 
            //������������Ϣ 
            if(returnValue){ 
            System.out.println("<----------- INFO: download " + remoteFilePath + "/" + remoteFileName + " from ftp �� succeed! ----------->"); 
            }else{ 
            System.out.println("<----------- ERR : download " + remoteFilePath + "/" + remoteFileName + " from ftp : failed! ----------->"); 
            } 
        } 
            //�ر����� 
            closeConnect(); 
        }catch(Exception e){ 
            e.printStackTrace(); 
            returnValue = false; 
            //������������Ϣ 
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

    //�ϴ��ļ�������ftp 
    public static boolean uploadAnnexToMpmsFtp(File uploadFile, String fileName,String path) throws IOException{ 
    boolean returnValue = false; 
    //�ϴ��ļ� 
        BufferedInputStream buffIn=null; 
        try{ 
        if(!uploadFile.exists()){ 
        System.out.println("<----------- ERR : annex named " + fileName + " not exist, upload failed! ----------->"); 
        return false; 
        }else{ 
        //�������� 
        connectToServer(); 
            //���ô���������ļ� 
            setFileType(FTP.BINARY_FILE_TYPE); 
            //����ļ� 
            buffIn=new BufferedInputStream(new FileInputStream(uploadFile)); 
            
            checkPathExist(path);
            
            //�ϴ��ļ���ftp 
            returnValue = ftpClient.storeFile(gbkToIso8859(path + "/" + fileName), buffIn); 
            //������������Ϣ 
            if(returnValue){ 
            System.out.println("<----------- INFO: upload file " + uploadFile.getAbsolutePath() + " to ftp : succeed! ----------->"); 
            }else{ 
            System.out.println("<----------- ERR : upload file " + uploadFile.getAbsolutePath() + " to ftp : failed! ----------->"); 
            } 
            //�ر����� 
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
     * ɾ�����������ļ� 
     * @param fileDir �ļ�·�� 
     * @param fileName �ļ����� 
     * @throws IOException 
     */ 
    private static boolean delFile(String fileDir, String fileName) throws IOException{ 
    boolean returnValue = false; 
    try{ 
        //����ftp������ 
            connectToServer(); 
    //��ת��ָ�����ļ�Ŀ¼ 
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
            //���ô���������ļ� 
            setFileType(FTP.BINARY_FILE_TYPE); 
            //��÷������ļ� 
            returnValue = ftpClient.deleteFile(fileName); 
            //�ر����� 
            closeConnect(); 
            //������������Ϣ 
            if(returnValue){ 
            System.out.println("<----------- INFO: delete " + fileDir + "/" + fileName + " at ftp:succeed! ----------->"); 
            }else{ 
            System.out.println("<----------- ERR : delete " + fileDir + "/" + fileName + " at ftp:failed! ----------->"); 
            } 
        }catch(Exception e){ 
            e.printStackTrace(); 
            returnValue = false; 
            //������������Ϣ 
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
