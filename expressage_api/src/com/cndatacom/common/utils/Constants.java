package com.cndatacom.common.utils;

public class Constants {
	//规范文档上传目录
	public static final String SPECIFICATION_EXCEL_FOLDER = "";
	//内容上传目录
	public static final String CONTENT_IMAGE_FOLDER = "com.cndatacom.iptv.pojo.ContentInfo.contentImage";
	
	//产品上传目录
	public static final String PRODUCT_IMAGE_FOLDER = "com.cndatacom.iptv.pojo.ProductInfo.iconPath";
	
	//SP资质营业执照扫描件
	public static final String SP_INFO_BUSLIC_IMG_FOLDER = "com.cndatacom.iptv.pojo.SpInfo.buslicImgPath" ;
	
	public static final String SP_INFO_BUSLIC_IMG_FOLDER2 = "com.cndatacom.iptv.pojo.SpInfo.buslicImgPath2" ;
	
	//SP服务管理文件上传目录
	public static final String CONTENT_INSTRUCTIONFILE_FOLDER = "com.cndatacom.iptv.pojo.SpServiceInfo.instructionFile";
	
	public static final String CONTENT_BUSSPLANFILE_FOLDER = "com.cndatacom.iptv.pojo.SpServiceInfo.bussplanfile";
	
	public static final String CONTENT_ICPFILE_FOLDER = "com.cndatacom.iptv.pojo.SpServiceInfo.icpFile";
	
	public static final String CONTENT_AUDIPPTFILE_FOLDER = "com.cndatacom.iptv.pojo.SpServiceInfo.audipptFile";
	
	public static final String CONTENT_COPYRIGHTFILE_FOLDER = "com.cndatacom.iptv.pojo.SpServiceInfo.copyrightFile";
	
	public static final String CONTENT_OTHERFILE_FOLDER = "com.cndatacom.iptv.pojo.SpServiceInfo.otherFile";
	
	//暂停常量
	public static final Long STOP_VALUE = 4L;
	
	//开通常量
	public static final Long ENABLE_VALUE = 5L;
	
	//删除常量
	public static final Long DELETE_VALUE = 9L;
	
	//未审核常量
	public static final Long UNAUDITED_VALUE = 1L;
	
	//审核通过常量
	public static final Long PASS_VALUE = 3L;
	
	//审核不通过常量
	public static final Long NOTPASS_VALUE = 2L;
	
	//路径上传目录
	public static final String VIDEO_FOLDER = "com.cndatacom.music.pojo.VideoInfo.videoDownloadPath";
	
	//数据导入记录数常量
	public static final String IMPORT_SIZE = "import.size";
	//上传目录 
	public static final String UPLOAD_PATH = "userupload.path";
	
	public static final String VERIFY_CODE_NAME = "randCode";
	
	// 调用web service时用到的全局key
	public static final String WS_KEY = "ws.key";
	
	//WebService中命名空间
	public static final String NS = "http://imusic.gdcn.com";
	
	//DES明文名称常量
	public static final String DES_PLAIN = "des.plain";
	
	public static final String IMUSIC_NAME = "爱音乐广东站";
	
	public static final Long DEFAULT_ROLE = 104L;
	
	public static final String SYSREGISTER_USERNAMES = "sysregister.usernames";
	
	public static final Long AUTH_SESSION_TYPE = 0L;
	
	public static final Long AUTH_PASSWORD_TYPE = 1L;
	
	//广告保存目录常量
	public static final String AD_CONTENT_FOLDER = "ad.content.folder";
	
	public static final Long DEFAULT_AD_CONTENT_VALUE = 0L;
	
	public static final Long COMMON_AD_CONTENT_VALUE = 1L;
	
	public static final String CONTENT_IMAGE_FORLDER = "content.image.folder";
	
	public static final String FCK_EDITOR_LINK_ADDRESS = "fck.editor.link.address";
	
	public static final String SITE_SUBJECT_ITEM_PATH = "com.cndatacom.music.pojo.SiteSubjectItem.subjectitemImgPath";
	
	//视频缩略图保存常量
	public static final String VIDEO_THUMB_PATH = "com.cndatacom.music.pojo.VideoInfo.thumbSavePath";
	
	public static final String ORDER_UP = "up";
	
	public static final String ORDER_DOWN = "down";
	
	//日志保存常量
	public static final Long BUSINESS_LOG_TYPE = 0L;
	
	public static final Long OTHER_LOG_TYPE = 1L;
	
	//SP组织架构类型
	public static final Long SP_GROUP_TYPE = 1L;
	
	public static final Long CARRIER_GROUP_TYPE = 0L;
	
	public static final Long REG_SP_ROLE = 2L;
	
	public static final Long ACTIVE_SP_ROLE = 3L;
	
	public static final Long SP_GROUP_PARENTID = 61L;
	
	///iptv用户session 变量名
	
	public static final String  IPTV_ACCOUNT="IPTV_ACCOUNT";
	
	//SMB常量配置
	public static final String SMB_DOMAIN = "smb.domain";
	
	public static final String SMB_USERNAME = "smb.username";
	
	public static final String SMB_PASSWORD = "smb.password";
	
	public static final String SMB_PATH = "smb.path";
	
	public static final String RESOURCE_PATH = "resource.path";
	
	//计费接口中常量配置
	public static final String LOG_FILE_BASEPATH = "log.file.basepath";
	
	public static final String BILL_FILE_BASEPATH = "bill.file.basepath";
	
	public static final String BILL_BUILD_MAXCOUNT = "bill.build.maxCount";
	
	public static final Long BILL_IS_EXCHANGED = 1L;
	
	public static final Long BILL_NOT_EXCHANGED = 0L;
	
	public static final Long BILL_WHITE_LIST_TYPE = 0L;
	
	/** 邮件附件保存目录（相对于web根目录） */
	public static final String MAIL_ATTACHMENT_DIR= "/MailAttachment";
	
}
