package com.ddxx.email.service;

import java.util.List;

public interface SendMailService {

	/**
	 * 发生简单文本邮件
	 * @param fromMail 邮件发送地址
	 * @param toMail   邮件接收地址
	 * @param ccMail   邮件抄送地址
	 * @param subject  邮件主题
	 * @param content  邮件内容
	 */
	void sendSimpleTextMail(String fromMail, String toMail, String ccMail, String subject, String content);

	/**
	 *  发生Html格式邮件，支持群发
	 * @param fromMail 邮件发送地址
	 * @param toMails  邮件接收地址
	 * @param ccMails  邮件抄送地址
	 * @param subject  邮件主题
	 * @param content  邮件内容
	 */
	void sendHtmlMail(String fromMail, List<String> toMails, List<String> ccMails, String subject, String content);

	/**
	 * 发生附件邮件，支持群发
	 * @param fromMail 邮件发送地址
	 * @param toMails  邮件接收地址
	 * @param ccMails  邮件抄送地址
	 * @param subject  邮件主题
	 * @param content  邮件内容
	 * @param path     附件地址
	 */
	void sendAttachmentMail(String fromMail, List<String> toMails, List<String> ccMails, String subject, String content,String path);

	/**
	 * 发生静态资源邮件
	 * @param fromMail 邮件发送地址
	 * @param toMails  邮件接收地址
	 * @param ccMails  邮件抄送地址
	 * @param subject  邮件主题
	 * @param content  邮件内容
	 * @param path     静态资源地址
	 * @param id       静态资源内嵌id
	 */
	void sendInlineResourceMail(String from, List<String> toMails, List<String> ccMails, String subject, String content,String path, String id);
	}
