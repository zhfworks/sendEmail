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
		//������֤��
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
//				������֤ fromUser  ��Ȩ��
				return new PasswordAuthentication("17602117025", "haifeng1992");
			}
		};
		//ʹ��JavaMail�����ʼ���5������
	    //1������session
	    Session session = Session.getInstance(props,auth);
	    //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
	    session.setDebug(true);
	    //2��ͨ��session�õ�transport����
	    Transport ts = session.getTransport();
	    //3�������ʼ�����������Ҫ�������ṩ������û��������������֤
	    //������Ȩ��
	    ts.connect("smtp.163.com", fromUser, "****");
	    //4�������ʼ�
	     Message message = createSimpleMail(session);
	     //5�������ʼ�
	     ts.sendMessage(message, message.getAllRecipients());
	     ts.close();
	}
	
	public static MimeMessage createSimpleMail(Session session) throws Exception {
		//�����ʼ�����
        MimeMessage message = new MimeMessage(session);
        //ָ���ʼ��ķ�����
         message.setFrom(new InternetAddress(fromUser));
        //ָ���ʼ����ռ��ˣ����ڷ����˺��ռ�����һ���ģ��Ǿ����Լ����Լ���
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(toUser));
         //�ʼ��ı���
         message.setSubject("java��������ʼ�");
         //�ʼ����ı�����
        message.setContent("��ð���","text/html;charset=UTF-8");
         //���ش����õ��ʼ�����
         return message;
	    }
}
