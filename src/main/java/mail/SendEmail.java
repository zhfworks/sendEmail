package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public static String fromUser ="17602117025@163.com";
	public static String toUser   ="18326902719@163.com";
	
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		//创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
//				密码验证 fromUser  授权码
				return new PasswordAuthentication("17602117025", "haifeng1992");
			}
		};
		//使用JavaMail发送邮件的5个步骤
	    //1、创建session
	    Session session = Session.getInstance(props,auth);
	    //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
	    session.setDebug(true);
	    //2、通过session得到transport对象
	    Transport ts = session.getTransport();
	    //3、连上邮件服务器，需要发件人提供邮箱的用户名和密码进行验证
	    //用了授权码
	    ts.connect("smtp.163.com", fromUser, "****");
	    //4、创建邮件
	     Message message = createSimpleMail(session);
	     //5、发送邮件
	     ts.sendMessage(message, message.getAllRecipients());
	     ts.close();
	}
	
	public static MimeMessage createSimpleMail(Session session) throws Exception {
		//创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
         message.setFrom(new InternetAddress(fromUser));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(toUser));
         //邮件的标题
         message.setSubject("java代码测试邮件");
         //邮件的文本内容
        message.setContent("你好啊！","text/html;charset=UTF-8");
         //返回创建好的邮件对象
         return message;
	    }
}
